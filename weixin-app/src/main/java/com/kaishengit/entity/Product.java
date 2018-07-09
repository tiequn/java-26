package com.kaishengit.entity;

/**
 * @author guojiabang
 * @date 2018/7/9 0009
 */
public class Product {

    private Integer id;
    private String productName;
    private String productInventory;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductInventory() {
        return productInventory;
    }

    public void setProductInventory(String productInventory) {
        this.productInventory = productInventory;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", productName='" + productName + '\'' +
                ", productInventory='" + productInventory + '\'' +
                '}';
    }
}
