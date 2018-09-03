package com.kaishengit.tms.service;

import com.kaishengit.tms.entity.Permission;
import com.kaishengit.tms.entity.Roles;

import java.util.List;

/**
 * @author guojiabang
 * @date 2018/8/31 0031
 */
public interface RolePermissionService {

    /**
     * 查找所有的权限
     * @return
     */
    List<Permission> findAllPermission();

    /**
     * 根据账号ID查询拥有的角色集合
     * @param id
     * @return
     */
    List<Roles> findRolesByAccountId(Integer id);

    /**
     * 根据角色ID查询所有的对应的权限
     * @return
     */
    List<Permission> findAllPermissionByRolesId(Integer rolesId);

    /**
     * 查询所有角色
     * @return
     */
    List<Roles> findAllRole();
}
