package com.kaishengit.tms.service;

import com.kaishengit.tms.entity.Permission;

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
}
