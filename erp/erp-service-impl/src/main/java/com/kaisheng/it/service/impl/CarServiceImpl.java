package com.kaisheng.it.service.impl;

import com.kaisheng.it.entity.Car;
import com.kaisheng.it.entity.CarExample;
import com.kaisheng.it.entity.Customer;
import com.kaisheng.it.entity.CustomerExample;
import com.kaisheng.it.exception.ServiceException;
import com.kaisheng.it.mapper.CarMapper;
import com.kaisheng.it.mapper.CustomerMapper;
import com.kaisheng.it.service.CarService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author guojiabang
 * @date 2018/8/2 0002
 */
public class CarServiceImpl implements CarService {

    Logger logger = LoggerFactory.getLogger(CarServiceImpl.class);

    @Autowired
    private CarMapper carMapper;

    @Autowired
    private CustomerMapper customerMapper;

    /**
     * 添加车辆信息
     * @param car
     * @param customer
     */
    @Override
    public void addCarIndo(Car car, Customer customer) {

        // 通过身份证号查询客户是否存在
        CustomerExample customerExample = new CustomerExample();
        customerExample.createCriteria().andIdCardEqualTo(customer.getIdCard());
        List<Customer> customerList = customerMapper.selectByExample(customerExample);

        Integer customerId = null;
        // 如果客户不存在则添加客户信息，获得自动生成的主键
        if(customerList == null || customerList.size() == 0){
            customerMapper.insertSelective(customer);
            customerId = customer.getId();
        } else{
            customerId = customerList.get(0).getId();
        }

         // 校验车辆是否存在
        CarExample carExample = new CarExample();
        carExample.createCriteria().andLicenceNoEqualTo(car.getLicenceNo());
        List<Car> carList = carMapper.selectByExample(carExample);

        Car carId = null;
        if(carList != null && !carList.isEmpty()){
            carId = carList.get(0);
        } else {
            // 不存在则添加车辆
            car.setCustomerId(customerId);
            carMapper.insertSelective(car);
            logger.info("添加车辆信息:{}", car);
        }

    }

    /**
     * 通过车牌号码查找车辆附带车主信息
     * @param licenceNo
     * @return
     */
    @Override
    public Car findCarInfoWithCustomer(String licenceNo) {

        if(StringUtils.isEmpty(licenceNo)){
            throw new ServiceException("系统异常");
        }

        Car car = carMapper.findWithCustomerByLicenceNO(licenceNo);
        return car;

    }

}
