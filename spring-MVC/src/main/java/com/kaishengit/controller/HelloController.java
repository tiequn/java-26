package com.kaishengit.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author guojiabang
 * @date 2018/7/19 0019
 */
@Controller
public class HelloController {

   // @RequestMapping(value = "/hello", method = {RequestMethod.GET,RequestMethod.POST})
    @GetMapping("/hello")  // 支持get请求
    // @PostMapping（"/hello"） // 支持post请求
    public String hello(){
        System.out.println("Hello --> springMVC");

        return "hello";
    }
}
