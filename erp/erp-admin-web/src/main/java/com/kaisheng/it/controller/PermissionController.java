package com.kaisheng.it.controller;

import com.kaisheng.it.controller.exceptionHandler.NotFoundException;
import com.kaisheng.it.dto.ResponseBean;
import com.kaisheng.it.entity.Permission;
import com.kaisheng.it.exception.ServiceException;
import com.kaisheng.it.service.RolePermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

/**
 * @author guojiabang
 * @date 2018/7/27 0027
 */
@Controller
@RequestMapping("/manage/permission")
public class PermissionController {

    @Autowired
    private RolePermissionService rolePermissionService;


    @GetMapping
    public String home(Model model){
        List<Permission> permissionList = rolePermissionService.findAllPermission();
        model.addAttribute("permissionList",permissionList);
        return "manage/permission/home";

    }

    @GetMapping("/new")
    public String permissionNew(Model model){

        // 封装所有菜单权限列表
        List<Permission> permissionList = rolePermissionService.findPermissionListByType(Permission.PERMISSION_TYPE_MENU);
        model.addAttribute("permissionList",permissionList);
        return "manage/permission/new";

    }

    @PostMapping("/new")
    public String permissionNew(Permission permission){

        rolePermissionService.savePermission(permission);
        return "redirect:/manage/permission";
    }

    @GetMapping("/{id:\\d+}/del")
    @ResponseBody
    public ResponseBean permissionDel(@PathVariable Integer id){

        try {
            rolePermissionService.delPermission(id);
        } catch (ServiceException e) {
            return ResponseBean.error(e.getMessage());
        }
        return ResponseBean.success();
    }

    @GetMapping("/{id:\\d+}/edit")
    public String edit(@PathVariable Integer id, Model model){

        Permission permission = rolePermissionService.findPermissionByid(id);

        if(permission == null){
            throw new NotFoundException();
        }

        // 封装所有菜单权限列表
        List<Permission> permissionList = rolePermissionService.findPermissionListByType(Permission.PERMISSION_TYPE_MENU);
        // 排除当前permission对象及其子类对象
        permissionList.remove(permission);

        model.addAttribute("permissionList",permissionList);
        model.addAttribute("permission",permission);
        return "manage/permission/edit";
    }

    @PostMapping("/{id:\\d+}/edit")
    public String edit(Permission permission, RedirectAttributes redirectAttributes){

        rolePermissionService.permissionEdit(permission);
        redirectAttributes.addFlashAttribute("message","编译成功");
        return "redirect:/manage/permission";
    }

}
