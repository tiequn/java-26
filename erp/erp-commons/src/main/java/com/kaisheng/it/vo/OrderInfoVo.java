package com.kaisheng.it.vo;

import com.kaisheng.it.entity.Order;
import com.kaisheng.it.entity.Parts;
import com.kaisheng.it.entity.ServiceType;

import java.util.List;

/**
 *  向前端展示订单数据的VO
 * @author guojiabang
 * @date 2018/8/7 0007
 */
public class OrderInfoVo {

    private Order order;
    private ServiceType serviceType;
    List<Parts> partsList;

    @Override
    public String toString() {
        return "orderInfoVo{" +
                "order=" + order +
                ", serviceType=" + serviceType +
                ", partsList=" + partsList +
                '}';
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public ServiceType getServiceType() {
        return serviceType;
    }

    public void setServiceType(ServiceType serviceType) {
        this.serviceType = serviceType;
    }

    public List<Parts> getPartsList() {
        return partsList;
    }

    public void setPartsList(List<Parts> partsList) {
        this.partsList = partsList;
    }
}
