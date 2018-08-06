package com.kaisheng.it.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kaisheng.it.entity.*;
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
        for (PartsVo partsVo : partsVoList){
            OrderParts orderParts = new OrderParts();
            orderParts.setOrderId(order.getId());
            orderParts.setPartsId(partsVo.getId());
            orderParts.setNum(partsVo.getNum());

            orderPartsMapper.insertSelective(orderParts);
        }

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


}
