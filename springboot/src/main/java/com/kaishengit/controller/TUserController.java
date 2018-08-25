package com.kaishengit.controller;

import com.github.pagehelper.PageHelper;
import com.kaishengit.entity.TUser;
import com.kaishengit.entity.TUserExample;
import com.kaishengit.mapper.TUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author guojiabang
 * @date 2018/8/23 0023
 */
@Controller
public class TUserController {

    @Autowired
    private TUserMapper tUserMapper;

    @GetMapping("/user")
    public @ResponseBody String count() {
        long count = tUserMapper.countByExample(new TUserExample());
        return "count from mybatis  : " + count;
    }

    @GetMapping("/all")
    public @ResponseBody List<TUser> list () {
        PageHelper.startPage(1,5);
        List<TUser> tUserList = tUserMapper.selectByExample(new TUserExample());
        return tUserList;
    }

}
