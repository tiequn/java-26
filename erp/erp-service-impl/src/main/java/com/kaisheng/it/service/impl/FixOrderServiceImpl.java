package com.kaisheng.it.service.impl;

import com.google.gson.Gson;
import com.kaisheng.it.dto.OrderInfoDto;
import com.kaisheng.it.dto.OrderStateDto;
import com.kaisheng.it.entity.*;
import com.kaisheng.it.exception.ServiceException;
import com.kaisheng.it.mapper.FixOrderMapper;
import com.kaisheng.it.mapper.FixOrderPartsMapper;
import com.kaisheng.it.service.FixOrderService;
import com.kaisheng.it.util.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import java.math.BigDecimal;
import java.util.List;

/**
 * @author guojiabang
 * @date 2018/8/9 0009
 */
@Service
public class FixOrderServiceImpl implements FixOrderService {

    @Autowired
    private FixOrderMapper fixOrderMapper;

    @Autowired
    private FixOrderPartsMapper fixOrderPartsMapper;

    @Autowired
    private JmsTemplate jmsTemplate;

    /**
     * 将队列中的数据解析生成维修订单
     * @param json
     */
    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public void createFixOrder(String json) {

        OrderInfoDto orderInfoDto = new Gson().fromJson(json,OrderInfoDto.class);

        // 解析OrderInfoDto信息入库
        // 封装维修单
        FixOrder fixOrder = new FixOrder();
        fixOrder.setOrderId(orderInfoDto.getOrder().getId());
        fixOrder.setOrderMoney(orderInfoDto.getOrder().getOrderMoney());
        fixOrder.setOrderTime(orderInfoDto.getOrder().getCreateTime());
        fixOrder.setState(orderInfoDto.getOrder().getState());
        fixOrder.setOrderType(orderInfoDto.getServiceType().getServiceName());
        fixOrder.setOrderServiceHour(orderInfoDto.getServiceType().getServiceHour());

        // 计算工时费
        fixOrder.setOrderServiceHourFee(new BigDecimal(Integer.parseInt(orderInfoDto.getServiceType().getServiceHour()) * Constant.DEFAULT_HOUR_FEE));

        // 计算配件的费用 (总钱数减去工时费等于配件费用)
        fixOrder.setOrderPartsFee(fixOrder.getOrderMoney().subtract(fixOrder.getOrderServiceHourFee()));

        // 封装车辆信息
        fixOrder.setCarColor(orderInfoDto.getOrder().getCar().getColor());
        fixOrder.setCarLicence(orderInfoDto.getOrder().getCar().getLicenceNo());
        fixOrder.setCarType(orderInfoDto.getOrder().getCar().getCarType());

        // 封装客户信息
        fixOrder.setCustomerName(orderInfoDto.getOrder().getCustomer().getUserName());
        fixOrder.setCustomerTel(orderInfoDto.getOrder().getCustomer().getTel());

        fixOrderMapper.insertSelective(fixOrder);

        // 配件列表入库
        // List<Parts> partsList = orderInfoDto.getPartsList();
        for (Parts parts : orderInfoDto.getPartsList()){

            // 创建FixOrderParts维修订单配件
            FixOrderParts fixOrderParts = new FixOrderParts();
            fixOrderParts.setOrderId(orderInfoDto.getOrder().getId());
            fixOrderParts.setPartsId(parts.getId());
            fixOrderParts.setPartsNo(parts.getPartsNo());
            fixOrderParts.setPartsName(parts.getPartsName());
            fixOrderParts.setPartsNum(parts.getNum());

            fixOrderPartsMapper.insertSelective(fixOrderParts);
        }

    }

    /**
     * 查询待维修列表
     * @return
     */
    @Override
    public List<FixOrder> findFixOrderListWithParts() {
        return fixOrderMapper.findListWithParts();
    }

    /**
     * 接收任务
     * @param id
     * @param employee
     * @throws ServiceException 还有未完成的任务，不能接收新任务
     */
    @Override
    public void tackReceive(Integer id, Employee employee) throws ServiceException {

        // 判断当前员工是否有未完成的任务
        FixOrderExample fixOrderExample = new FixOrderExample();
        fixOrderExample.createCriteria().andOrderIdEqualTo(id).andStateEqualTo(FixOrder.ORDER_STATE_FIXING);
        List<FixOrder> fixOrderList = fixOrderMapper.selectByExample(fixOrderExample);

        if(fixOrderList != null && fixOrderList.size() > 0){
            throw new ServiceException("还有未完成任务，不能接受新任务");
        }

        FixOrder fixOrder = fixOrderMapper.selectByPrimaryKey(id);
        if(fixOrder == null){
            throw new ServiceException("参数错误或该订单不存在");
        }

        fixOrder.setState(FixOrder.ORDER_STATE_FIXING);
        fixOrder.setFixEmployeeId(employee.getId());
        fixOrder.setFixEmployeeName(employee.getEmployeeName());

        fixOrderMapper.updateByPrimaryKeySelective(fixOrder);

        OrderStateDto orderStateDto = new OrderStateDto();
        orderStateDto.setOrderId(id);
        orderStateDto.setEmployeeId(employee.getId());
        orderStateDto.setState(FixOrder.ORDER_STATE_FIXING);

        sendStateToMQ(orderStateDto);

    }

    /**
     * 获取维修订单详情
     * @param id
     * @return
     */
    @Override
    public FixOrder findFixOrderById(Integer id) {
        return fixOrderMapper.findWithPartsById(id);
    }

    /**
     * 完成顶单维修
     * @param id
     */
    @Override
    public void tackDone(Integer id) throws ServiceException {
        // 获取维修订单
        FixOrder fixOrder = fixOrderMapper.findWithPartsById(id);
        if(fixOrder == null){
            throw new ServiceException("参数异常或该订单不存在");
        }

        fixOrder.setState(FixOrder.ORDER_STATE_FIXED);
        fixOrderMapper.updateByPrimaryKey(fixOrder);

        OrderStateDto orderStateDto = new OrderStateDto();
        orderStateDto.setState(FixOrder.ORDER_STATE_FIXED);
        orderStateDto.setOrderId(id);

        // 发送订单状态消息队列
        sendStateToMQ(orderStateDto);

    }

    /**
     * 发送订单状态到消息队列
     * @param orderStateDto
     */
    private void sendStateToMQ(OrderStateDto orderStateDto) {
        // 将对象转成json数据传输到mq中
        String json = new Gson().toJson(orderStateDto);
        jmsTemplate.send("state-queue", new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                return session.createTextMessage(json);
            }
        });

    }


}
