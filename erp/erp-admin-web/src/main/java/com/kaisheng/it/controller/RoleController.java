package com.kaisheng.it.controller;

import com.kaisheng.it.controller.exceptionHandler.NotFoundException;
import com.kaisheng.it.dto.ResponseBean;
import com.kaisheng.it.entity.Permission;
import com.kaisheng.it.entity.Role;
import com.kaisheng.it.exception.ServiceException;
import com.kaisheng.it.mapper.RoleMapper;
import com.kaisheng.it.service.RolePermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Map;

/**
 * @author guojiabang
 * @date 2018/7/27 0027
 */
@Controller
@RequestMapping("/manage/roles")
public class RoleController {

    @Autowired
    private RolePermissionService rolePermissionService;

    @GetMapping
    public String home(Model model){

        List<Role> roleList = rolePermissionService.findRoleList();
        model.addAttribute("roleList",roleList);
        return "manage/roles/home";
    }

    @GetMapping("/new")
    public String roleNew(Model model){
       List<Permission> permissionList = rolePermissionService.findAllPermission();
       model.addAttribute("permissionList",permissionList);
        return "manage/roles/new";
    }

    @PostMapping("/new")
    public String rolesNew(Role role, Integer[] permissionId, RedirectAttributes redirectAttributes)throws ServiceException{
        try {
            rolePermissionService.saveRole(role, permissionId);
        } catch (ServiceException e) {
            redirectAttributes.addFlashAttribute("message",e.getMessage());
        }
        return "redirect:/manage/roles";
    }

    @GetMapping("/{id:\\d+}/del")
    @ResponseBody
    public ResponseBean delRole(@PathVariable Integer id){

        try {
            rolePermissionService.delRoleById(id);
            return ResponseBean.success();
        } catch (ServiceException e) {
            return  ResponseBean.error(e.getMessage());
        }
    }

    @GetMapping("/{id:\\d+}/edit")
    public String editRole(@PathVariable Integer id, Model model){
        Role role = rolePermissionService.findRoleWithPermission(id);

        if(role == null){
            throw new NotFoundException();
        }

        // 获得是否被checked的权限列表
        Map<Permission, Boolean> permissionBooleanMap = rolePermissionService.permissionBooleanMap(role.getPermissionList());

        model.addAttribute("permissionBooleanMap",permissionBooleanMap);

        model.addAttribute("role",role);
        return "manage/roles/edit";
    }

    @PostMapping("/{id:\\d+}/edit")
    public String editRole(Role role, Integer[] permissionId){

        rolePermissionService.editRole(role,permissionId);

        return "redirect:/manage/roles";
    }




}
