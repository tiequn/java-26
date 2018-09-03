package com.kaishengit.tms.impl;

import com.alibaba.dubbo.common.extension.Activate;
import com.alibaba.dubbo.config.annotation.Service;
import com.google.common.collect.Collections2;
import com.google.common.collect.Lists;
import com.kaishengit.tms.entity.*;
import com.kaishengit.tms.mapper.PermissionMapper;
import com.kaishengit.tms.mapper.RolesMapper;
import com.kaishengit.tms.mapper.RolesPermissionMapper;
import com.kaishengit.tms.service.RolePermissionService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * 角色和权限的业务类
 * @author guojiabang
 * @date 2018/8/31 0031
 */
@Service(version = "1.0", timeout = 5000)
public class RolePermissionServiceImpl implements RolePermissionService {

    @Autowired
    private PermissionMapper permissionMapper;

    @Autowired
    private RolesPermissionMapper rolesPermissionMapper;

    @Autowired
    private RolesMapper rolesMapper;

    /**
     * 查找所有的权限
     * @return
     */
    @Override
    public List<Permission> findAllPermission() {

        PermissionExample permissionExample = new PermissionExample();
        List<Permission> permissionList = permissionMapper.selectByExample(permissionExample);
        List<Permission> resultList = new ArrayList<>();
        treeList(permissionList,resultList,0);
        return resultList;
    }

    /**
     * 根据账号ID查询拥有的角色集合
     * @param id
     * @return
     */
    @Override
    public List<Roles> findRolesByAccountId(Integer id) {

        return rolesMapper.findRolesByAccountId(id);
    }

    /**
     * 根据角色ID查询所有的对应的权限
     * @return
     */
    @Override
    public List<Permission> findAllPermissionByRolesId(Integer rolesId) {
        return permissionMapper.findAllPermissionByRolesId(rolesId);
    }

    /**
     * 查询所有角色
     * @return
     */
    @Override
    public List<Roles> findAllRole() {
        RolesExample rolesExample = new RolesExample();
        return rolesMapper.selectByExample(rolesExample);
    }

    /**
     * 将查询数据库的角色列表转换为树形集合结果
     * @param sourceList 数据库查询出的集合
     * @param endList 转换结束的结果集合
     * @param parentId 父ID
     */
    private void treeList(List<Permission> sourceList, List<Permission> endList, int parentId) {

        List<Permission> tempList = Lists.newArrayList(Collections2.filter(sourceList, permission -> permission.getParentId().equals(parentId)));

        for (Permission permission : tempList){
            endList.add(permission);
            treeList(sourceList,endList,permission.getId());

        }

    }
}
