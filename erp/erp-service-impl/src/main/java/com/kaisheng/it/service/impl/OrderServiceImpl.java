package com.kaisheng.it.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.gson.Gson;
import com.kaisheng.it.dto.OrderInfoDto;
import com.kaisheng.it.dto.OrderStateDto;
import com.kaisheng.it.entity.*;
import com.kaisheng.it.exception.ServiceException;
import com.kaisheng.it.mapper.*;
import com.kaisheng.it.service.OrderService;
import com.kaisheng.it.util.Constant;
import com.kaisheng.it.vo.OrderVo;
import com.kaisheng.it.vo.PartsVo;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.transaction.annotation.Transactional;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * @author guojiabang
 * @date 2018/8/4 0004
 */
public class OrderServiceImpl implements OrderService {

    Logger logger = LoggerFactory.getLogger(OrderServiceImpl.class);

    @Autowired
    private ServiceTypeMapper serviceTypeMapper;

    @Autowired
    private TypeMapper typeMapper;

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private OrderEmployeeMapper orderEmployeeMapper;

    @Autowired
    private OrderPartsMapper orderPartsMapper;

    @Autowired
    private PartsMapper partsMapper;

    @Autowired
    private JmsTemplate jmsTemplate;

    @Autowired
    private CountDailyMapper countDailyMapp;

    /**
     * 查询所有类型
     * @return
     */
    @Override
    public List<ServiceType> findAllSericeType() {
        ServiceTypeExample serviceTypeExample = new ServiceTypeExample();
        return serviceTypeMapper.selectByExample(serviceTypeExample);

    }

    /**
     * 获得所有的配件类型
     * @return
     */
    @Override
    public List<Type> findAllPartsType() {

        TypeExample typeExample = new TypeExample();
        return typeMapper.selectByExample(typeExample);

    }

    /**
     * 新增订单
     * @param orderVo
     */
    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public void saveOrder(OrderVo orderVo, Integer employeeId) {

        // 新增订单表
        Order order = new Order();
        order.setCarId(orderVo.getCarId());
        order.setServiceTypeId(orderVo.getServiceTypeId());
        order.setOrderMoney(orderVo.getFee());
        order.setState(Order.ORDER_STATE_NEW);

        orderMapper.insertSelective(order);

        // 新增订单和员工的关联关系表
        OrderEmployee orderEmployee = new OrderEmployee();
        orderEmployee.setOrderId(order.getId());
        orderEmployee.setEmployeeId(employeeId);

        orderEmployeeMapper.insertSelective(orderEmployee);

        // 新增订单和配件的关系表
        List<PartsVo> partsVoList = orderVo.getPartsLists();
        addOrderParts(order.getId(),partsVoList);

        logger.info("{}新增订单{}", employeeId, order.getId());

    }

    /**
     * 通过参数查询订单
     *
     * @param queryMap
     * @return
     */
    @Override
    public PageInfo<Order> findPageByParam(Map<String, Object> queryMap) {

        PageHelper.startPage(Integer.parseInt(String.valueOf(queryMap.get("pageNo"))),Constant.DEFAULT_PAGE_SIZE);

        List<Order> orderList = orderMapper.findUndonePageByParam(queryMap);

        PageInfo<Order> orderPageInfo = new PageInfo<>(orderList);
        return orderPageInfo;
    }

    /**
     * 查询订单，车辆，顾客信息
     * @param id
     * @return
     */
    @Override
    public Order findOrderWithCarByCustomerById(Integer id) {

        return orderMapper.findOrderWithCarByCustomerById(id);
    }

    /**
     * 获得订单服务类型
     * @param serviceTypeId
     * @return
     */
    @Override
    public ServiceType findOrderWithServicerTypeByid(Integer serviceTypeId) {
        return serviceTypeMapper.selectByPrimaryKey(serviceTypeId);
    }

    /**
     * 删除订单
     * @param id
     */
    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public void delOrderById(Integer id) throws SecurityException{

        // 删除订单和配件关联关系表
        orderPartsMapper.deleteByPrimaryKey(id);

        // 删除订单和员工关联关系表
        OrderEmployeeExample orderEmployeeExample = new OrderEmployeeExample();
        orderEmployeeExample.createCriteria().andOrderIdEqualTo(id);
        orderEmployeeMapper.deleteByExample(orderEmployeeExample);

        Order order = orderMapper.selectByPrimaryKey(id);
        if(order.getState().equals(Order.ORDER_STATE_DONE)){
            throw new ServiceException("该订单已完成不可删除");
        } else {
            // 删除订单表
            orderMapper.deleteByPrimaryKey(id);
        }
    }

    /**
     * 订单下发
     * @param id
     */
    @Override
    public void findOrderByTrans(Integer id)throws ServiceException {
        Order order = orderMapper.selectByPrimaryKey(id);
        if(order == null){
            throw new ServiceException("参数异常或订单不存在");
        }

        if(!order.getState().equals(Order.ORDER_STATE_NEW)){
            throw new ServiceException("该订单已生成并下发,操作失败");
        }

        // 设置订单状态为已下发
        order.setState(Order.ORDER_STATE_TRANS);
        orderMapper.updateByPrimaryKeySelective(order);

        // 订单下发后将订单消息发送到消息队列
        sendOrderInfoToMq(id);

    }

    /**
     * 发送订单详情信息到队列中
     * @param id
     */
    private void sendOrderInfoToMq(Integer id) {
        // 获得订单
        Order order = orderMapper.findOrderWithCarByCustomerById(id);

        // 获得订单服务类型信息
        ServiceType serviceType = serviceTypeMapper.selectByPrimaryKey(order.getServiceTypeId());

        // 获得订单配件列表
        List<Parts> partsList = partsMapper.findPartsByOrderId(order.getId());

        OrderInfoDto orderInfoDto = new OrderInfoDto();
        orderInfoDto.setOrder(order);
        orderInfoDto.setPartsList(partsList);
        orderInfoDto.setServiceType(serviceType);

        // 转成json发送到mq队列中
        String json = new Gson().toJson(orderInfoDto);

        jmsTemplate.send(new MessageCreator(){
            @Override
            public Message createMessage(Session session) throws JMSException {
                return session.createTextMessage(json);
            }
        });
    }

    /**
     * 更新订单
     * @param orderVo
     */
    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public void editOrder(OrderVo orderVo) throws ServiceException{

        // 获取订单
        Order order = orderMapper.selectByPrimaryKey(orderVo.getId());

        // 判断订单是否为空
        if(order == null){
            throw new ServiceException("参数异常或订单不存在");
        }

        // 判断状态是否为1 ，不为1 不可修改
        if(!order.getState().equals(Order.ORDER_STATE_NEW)){
            throw new ServiceException("该订单已生成，无法修改");
        }

        order.setCarId(orderVo.getCarId());
        order.setOrderMoney(orderVo.getFee());
        order.setServiceTypeId(orderVo.getServiceTypeId());

        orderMapper.updateByPrimaryKeySelective(order);

        // 删除原有订单和配件的关联关系表
        OrderPartsExample orderPartsExample = new OrderPartsExample();
        orderPartsExample.createCriteria().andOrderIdEqualTo(orderVo.getId());
        orderPartsMapper.deleteByExample(orderPartsExample);

        // 新增订单和配件的关联关系表
        List<PartsVo> partsVoList = orderVo.getPartsLists();
        addOrderParts(order.getId(),partsVoList);

        logger.info("更新订单{}", order.getId());

    }

    /**
     * 解析json数据改变订单状态
     * @param json
     */
    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public void editOrderState(String json) {
        OrderStateDto orderStateDto = new Gson().fromJson(json, OrderStateDto.class);
        Order order = orderMapper.selectByPrimaryKey(orderStateDto.getOrderId());

        if (order == null) {
            logger.error("{}订单不存在", orderStateDto.getOrderId());
        }

        // 更改基础表的订单状态 并更新订单
        order.setState(orderStateDto.getState());
        orderMapper.updateByPrimaryKey(order);

        // 新增订单操作员工
        // 如果员工的employeeId==null 则代表员工订单关联关系不需要新增
        if(orderStateDto.getEmployeeId() != null){
            OrderEmployee orderEmployee = new OrderEmployee();
            orderEmployee.setEmployeeId(orderStateDto.getEmployeeId());
            orderEmployee.setOrderId(orderStateDto.getOrderId());

            orderEmployeeMapper.insertSelective(orderEmployee);
        }

    }

    /**
     * 统计每天的订单数量和金额
     */
    @Override
    public void countDailyOrder() {
        DateTimeFormatter fmt = DateTimeFormat.forPattern("yyyy-MM-dd");

        // 1.获取做昨天的所有以完成的订单
        // 1.1获取当前时间
        DateTime dt = new DateTime();
        // 1.2 当前时间减去一天获取昨天时间
        dt = dt.minusDays(1);
        // 1.3 格式化日期并转为String
        String dateTime = fmt.print(dt);
        // 1.4 获得昨天的数据
        List<Order> orderList = orderMapper.findDailyOrderBySate(Order.ORDER_STATE_DONE,dateTime);
        if(orderList != null && orderList.size() > 0){
            // 根据所有订单获取当天订单
            CountDaily countDaily = new CountDaily();
            countDaily.setSumnum(orderList.size());
            countDaily.setDateTime(dateTime);

            // 统计昨天订单金额
            BigDecimal bigDecimal = BigDecimal.ZERO;
            for (Order order : orderList){
                bigDecimal = bigDecimal.add(order.getOrderMoney());
            }
            countDaily.setSumminey(bigDecimal);
            // 添加数据库
            countDailyMapp.insertSelective(countDaily);

        } else{
            CountDaily countDaily = new CountDaily();
            countDaily.setDateTime(dateTime);
            // 添加数据库
            countDailyMapp.insertSelective(countDaily);

        }
        logger.debug("统计{}的订单数据",dateTime);
    }


    /**
     * 新增订单和配件的关联关系表
     */
    private void addOrderParts(Integer orderId, List<PartsVo> partsVoList) {
        for (PartsVo partsVo : partsVoList){
            OrderParts orderParts = new OrderParts();
            orderParts.setOrderId(orderId);
            orderParts.setPartsId(partsVo.getId());
            orderParts.setNum(partsVo.getNum());

            orderPartsMapper.insertSelective(orderParts);
        }

    }


}
