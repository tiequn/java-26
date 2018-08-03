package com.kaisheng.it.service;

import com.kaisheng.it.entity.Car;
import com.kaisheng.it.entity.Customer;

/**
 * @author guojiabang
 * @date 2018/8/2 0002
 */
public interface CarService {

    /**
     *添加车辆信息
     * @param car
     * @param customer
     */
    void addCarIndo(Car car, Customer customer);

    /**
     * 通过车牌号码查找车辆附带车主信息
     * @param licenceNo
     * @return
     */
    Car findCarInfoWithCustomer(String licenceNo);
}
