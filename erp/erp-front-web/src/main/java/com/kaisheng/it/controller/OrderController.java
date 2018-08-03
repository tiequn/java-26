package com.kaisheng.it.controller;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.PostConstruct;

/**
 * @author guojiabang
 * @date 2018/8/2 0002
 */
@Controller
@RequestMapping("/order")
public class OrderController {

    @GetMapping("/list")
    public String undoneList(){

        return "order/list";
    }

    @GetMapping("/new")
    public String newOrder(){

        return "order/new";
    }

    @PostMapping("/new")
    public String newOrder(Order order){

        return "";
    }


}
