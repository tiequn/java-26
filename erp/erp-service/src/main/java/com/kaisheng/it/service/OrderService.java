package com.kaisheng.it.service;

import com.github.pagehelper.PageInfo;
import com.kaisheng.it.entity.Order;
import com.kaisheng.it.entity.Parts;
import com.kaisheng.it.entity.ServiceType;
import com.kaisheng.it.entity.Type;
import com.kaisheng.it.vo.OrderVo;

import java.util.List;
import java.util.Map;

/**
 * @author guojiabang
 * @date 2018/8/4 0004
 */
public interface OrderService {

    /**
     * 查询所有类型
     * @return
     */
    List<ServiceType> findAllSericeType();

    /**
     * 获得所有的配件类型
     * @return
     */
    List<Type> findAllPartsType();

    /**
     * 新增订单
     * @param orderVo
     */
    void saveOrder(OrderVo orderVo, Integer employeeId);

    /**
     * 通过参数查询订单
     * @param queryMap
     * @return
     */
    PageInfo<Order> findPageByParam(Map<String,Object> queryMap);
}
