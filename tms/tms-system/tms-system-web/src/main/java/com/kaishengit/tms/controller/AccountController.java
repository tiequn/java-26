package com.kaishengit.tms.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.google.common.collect.Maps;
import com.kaishengit.tms.entity.Account;
import com.kaishengit.tms.entity.Roles;
import com.kaishengit.tms.service.AccountService;
import com.kaishengit.tms.service.RolePermissionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

/**
 * 账号管理控制器
 * @author guojiabang
 * @date 2018/8/31 0031
 */
@Controller
@RequestMapping("/manage/account")
public class AccountController {

    @Reference(version = "1.0" )
    private AccountService accountService;

    @Reference(version = "1.0")
    private RolePermissionService rolePermissionService;

    @GetMapping
    public String home(
            @RequestParam(required = false) Integer rolesId,
            @RequestParam(required = false) String nameMobile,
            Model model){

        Map<String,Object> requestParam = Maps.newHashMap();
        requestParam.put("rolesId",rolesId);
        requestParam.put("nameMobile",nameMobile);

        List<Account> accountList = accountService.findAllAccountWithRolesByQueryParam(requestParam);
        List<Roles> rolesList = rolePermissionService.findAllRole();

        model.addAttribute("accountList",accountList);
        model.addAttribute("rolesList",rolesList);
        return "/manage/account/home";
    }

}
