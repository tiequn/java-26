package com.kaisheng.it.controller;

import com.kaisheng.it.dto.ResponseBean;
import com.kaisheng.it.entity.Car;
import com.kaisheng.it.entity.Customer;
import com.kaisheng.it.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author guojiabang
 * @date 2018/8/2 0002
 */
@Controller
@RequestMapping("/car")
public class CarController {

    @Autowired
    private CarService carService;

    @PostMapping("/new")
    @ResponseBody
    public ResponseBean newCarInfo(Car car, Customer customer){

        carService.addCarIndo(car,customer);
        car.setCustomer(customer);

        return ResponseBean.success(car);
    }

    @GetMapping("/check")
    @ResponseBody
    public ResponseBean checkCarInfo(String licenceNo){

        // 根据车牌号码查询车辆信息
        Car car = carService.findCarInfoWithCustomer(licenceNo);

        if(car != null){
            return ResponseBean.success(car);
        } else{
            return ResponseBean.error("暂未输入");
        }


    }

}
