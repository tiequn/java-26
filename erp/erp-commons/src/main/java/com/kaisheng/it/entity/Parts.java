package com.kaisheng.it.entity;

import java.io.Serializable;
import java.math.BigDecimal;

public class Parts implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    private String partsNo;

    private String partsName;

    private BigDecimal inPrice;

    private BigDecimal salePrice;

    private Integer inventory;

    private Integer typeId;

    private String address;

    private Type type;

    private Integer num;

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPartsNo() {
        return partsNo;
    }

    public void setPartsNo(String partsNo) {
        this.partsNo = partsNo;
    }

    public String getPartsName() {
        return partsName;
    }

    public void setPartsName(String partsName) {
        this.partsName = partsName;
    }

    public BigDecimal getInPrice() {
        return inPrice;
    }

    public void setInPrice(BigDecimal inPrice) {
        this.inPrice = inPrice;
    }

    public BigDecimal getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(BigDecimal salePrice) {
        this.salePrice = salePrice;
    }

    public Integer getInventory() {
        return inventory;
    }

    public void setInventory(Integer inventory) {
        this.inventory = inventory;
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Parts{" +
                "id=" + id +
                ", partsNo='" + partsNo + '\'' +
                ", partsName='" + partsName + '\'' +
                ", inPrice=" + inPrice +
                ", salePrice=" + salePrice +
                ", inventory=" + inventory +
                ", typeId=" + typeId +
                ", address='" + address + '\'' +
                ", type=" + type +
                ", num=" + num +
                '}';
    }
}