package com.kaisheng.it.dto;

import com.kaisheng.it.entity.Order;
import com.kaisheng.it.entity.Parts;
import com.kaisheng.it.entity.ServiceType;

import java.util.List;

/**
 * @author guojiabang
 * @date 2018/8/9 0009
 */
public class OrderInfoDto {

    private Order order;
    private ServiceType serviceType;
    List<Parts> partsList;

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
