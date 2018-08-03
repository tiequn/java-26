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
    public String newCarInfo(Car car, Customer customer, Model model){

        carService.addCarIndo(car,customer);

        model.addAttribute("car",car);
        model.addAttribute("customer",customer);
        return "order/new";
    }

    @GetMapping("/check")
    @ResponseBody
    public ResponseBean checkCarInfo(String licenceNo){

        Car car = carService.findCarInfoWithCustomer(licenceNo);

        if(car != null){
            return ResponseBean.success(car);
        } else{
            return ResponseBean.error("暂未输入");
        }


    }

}
