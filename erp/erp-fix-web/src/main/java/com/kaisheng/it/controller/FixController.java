package com.kaisheng.it.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author guojiabang
 * @date 2018/8/8 0008
 */
@Controller
@RequestMapping("/fix")
public class FixController {

    @GetMapping("/list")
    public String fixList(){

        return "/fix/list";  
    }

}
