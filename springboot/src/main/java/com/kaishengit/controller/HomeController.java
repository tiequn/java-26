package com.kaishengit.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.awt.peer.SystemTrayPeer;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author guojiabang
 * @date 2018/8/22 0022
 */
@Controller
public class HomeController {

    @GetMapping("/home")
    public String home(Model model){
        model.addAttribute("name","王二");
        model.addAttribute("age",23);

        List<String> nameList = Arrays.asList("tom","jack","lisi","王二");

        model.addAttribute("nameList",nameList);

        return "home";
    }

    @GetMapping("/new")
    public String newForm(Model model){

        model.addAttribute("name","tom");
        model.addAttribute("role","admin");
        model.addAttribute("id",1000);



        return "new";
    }

    @GetMapping("/demo")
    public String demo(Model model){
        model.addAttribute("message","<em style='color:red'>Hello,SpringBoot!</em>");

        model.addAttribute("id","1000");
        model.addAttribute("name","tom");

        Map<String,String> map = new HashMap<>();
        map.put("id","1001");
        map.put("name","jack");
        map.put("address","jiaozuo");

        model.addAttribute("map",map);

        model.addAttribute("time",System.currentTimeMillis());
        model.addAttribute("money",1234567899887654321L);
        model.addAttribute("bitcoin",new BigDecimal("0.0000"));


        return "demo";
    }



}
