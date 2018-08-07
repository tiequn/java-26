package com.kaisheng.it.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kaisheng.it.entity.*;
import com.kaisheng.it.exception.ServiceException;
import com.kaisheng.it.mapper.*;
import com.kaisheng.it.service.OrderService;
import com.kaisheng.it.util.Constant;
import com.kaisheng.it.vo.OrderVo;
import com.kaisheng.it.vo.PartsVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * @author guojiabang
 * @date 2018/8/4 0004
 */
@Service
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
    public void findOrderByTrans(Integer id) {
        Order order = orderMapper.selectByPrimaryKey(id);
        order.setState(Order.ORDER_STATE_TRANS);
        orderMapper.updateByPrimaryKeySelective(order);

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
     * 新增订单和配件的关联关系表
     *
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
