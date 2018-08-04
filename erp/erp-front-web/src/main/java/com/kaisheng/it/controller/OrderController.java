package com.kaisheng.it.controller;

import com.google.gson.Gson;
import com.kaisheng.it.dto.ResponseBean;
import com.kaisheng.it.entity.Parts;
import com.kaisheng.it.entity.ServiceType;
import com.kaisheng.it.entity.Type;
import com.kaisheng.it.service.OrderService;
import com.kaisheng.it.service.PartsService;
import com.kaisheng.it.vo.OrderVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * @author guojiabang
 * @date 2018/8/2 0002
 */
@Controller
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private PartsService partsService;

    @GetMapping("/list")
    public String undoneList(){

        return "order/list";
    }

    @GetMapping("/new")
    public String newOrder(){

        return "order/new";
    }

    @PostMapping("/new")
    @ResponseBody
    public ResponseBean newOrder(String json){

        Gson gson = new Gson();
        gson.fromJson(json,OrderVo.class);

        return ResponseBean.success();
    }

    @GetMapping("/service/types")
    @ResponseBody
    public ResponseBean serviceTypes(){
        List<ServiceType> serviceTypeList = orderService.findAllSericeType();
       return ResponseBean.success(serviceTypeList);

    }

    @GetMapping("/parts/types")
    @ResponseBody
    public ResponseBean partsTypes(){
        List<Type> partsList = orderService.findAllPartsType();
        return ResponseBean.success(partsList);

    }

    @GetMapping("/{id:\\d+}/parts")
    @ResponseBody
    public ResponseBean partsByType(@PathVariable Integer id){
        List<Parts> partsList = partsService.findPartsByType(id);
        return ResponseBean.success(partsList);
    }

}
