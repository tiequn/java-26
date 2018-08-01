package com.kaisheng.it.controller;

import com.google.common.collect.Lists;
import com.kaisheng.it.controller.exceptionHandler.NotFoundException;
import com.kaisheng.it.dto.ResponseBean;
import com.kaisheng.it.entity.Permission;
import com.kaisheng.it.exception.ServiceException;
import com.kaisheng.it.service.RolePermissionService;
import com.kaisheng.it.shiro.CustomerFilterChainDefinition;
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

    @Autowired
    private CustomerFilterChainDefinition customerFilterChainDefinition;


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
        // 刷新权限
        customerFilterChainDefinition.updatePermission();
        return "redirect:/manage/permission";
    }

    @GetMapping("/{id:\\d+}/del")
    @ResponseBody
    public ResponseBean permissionDel(@PathVariable Integer id){

        try {
            rolePermissionService.delPermission(id);
            // 刷新权限
            customerFilterChainDefinition.updatePermission();
        } catch (ServiceException e) {
            return ResponseBean.error(e.getMessage());
        }
        return ResponseBean.success();
    }

    @GetMapping("/{id:\\d+}/edit")
    public String edit(@PathVariable Integer id, Model model){

        // 获得权限
        Permission permission = rolePermissionService.findPermissionByid(id);

        // 判断权限是否为null
        if(permission == null){
            throw new NotFoundException();
        }

        // 封装所有菜单权限列表
        List<Permission> permissionList = rolePermissionService.findPermissionListByType(Permission.PERMISSION_TYPE_MENU);
        // 排除当前permission对象及其子类对象
        remove(permissionList,permission);

        model.addAttribute("permissionList",permissionList);
        model.addAttribute("permission",permission);
        return "manage/permission/edit";
    }

    /**
     * 使用递归去除所有子权限
     * @param permissionList  源List
     * @param permission  要去除的权限对象
     */
    private void remove(List<Permission> permissionList, Permission permission) {

        // 通过临时变量来存储所有的list元素防止漏删
        List<Permission> temp = Lists.newArrayList(permissionList);
        for(int i = 0; i < temp.size(); i++){
            // 判断有没有子权限
            if(temp.get(i).getPid().equals(permission.getId())){
                remove(permissionList,temp.get(i));
            }
        }
        // 判断有没有子权限要去除
        permissionList.remove(permission);
    }

    @PostMapping("/{id:\\d+}/edit")
    public String edit(Permission permission, RedirectAttributes redirectAttributes){

        rolePermissionService.permissionEdit(permission);
        redirectAttributes.addFlashAttribute("message","编译成功");
        // 刷新权限
        customerFilterChainDefinition.updatePermission();
        return "redirect:/manage/permission";
    }

}
