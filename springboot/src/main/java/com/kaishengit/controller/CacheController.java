package com.kaishengit.controller;

import com.kaishengit.entity.TUser;
import com.kaishengit.service.TUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author guojiabang
 * @date 2018/8/25 0025
 */
@RestController
@RequestMapping("/user")
public class CacheController {

    @Autowired
    private TUserService tUserService;

    @GetMapping("/{id:\\d+}")
    public TUser findById(@PathVariable Integer id){
        return tUserService.findById(id);
    }

    @GetMapping("/{id:\\d+}/del")
    public String findDel(@PathVariable Integer id){
        tUserService.findBydel(id);
        return "success";
    }

}
