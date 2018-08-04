package com.kaisheng.it.vo;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author guojiabang
 * @date 2018/8/4 0004
 */
public class OrderVo {

    private Integer carId;
    private Integer serviceTypeId;
    private BigDecimal fee;
    private List<PartsVo> partsLists;

    @Override
    public String toString() {
        return "OrderVo{" +
                "carId=" + carId +
                ", serviceTypeId=" + serviceTypeId +
                ", fee=" + fee +
                ", partsLists=" + partsLists +
                '}';
    }

    public Integer getCarId() {
        return carId;
    }

    public void setCarId(Integer carId) {
        this.carId = carId;
    }

    public Integer getServiceTypeId() {
        return serviceTypeId;
    }

    public void setServiceTypeId(Integer serviceTypeId) {
        this.serviceTypeId = serviceTypeId;
    }

    public BigDecimal getFee() {
        return fee;
    }

    public void setFee(BigDecimal fee) {
        this.fee = fee;
    }

    public List<PartsVo> getPartsLists() {
        return partsLists;
    }

    public void setPartsLists(List<PartsVo> partsLists) {
        this.partsLists = partsLists;
    }
}
