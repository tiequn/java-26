package com.kaisheng.it.service;

import com.kaisheng.it.entity.Permission;
import com.kaisheng.it.entity.Role;

import java.util.List;
import java.util.Map;

/**
 *
 * 控制业务层
 * @author guojiabang
 * @date 2018/7/27 0027
 */

public interface RolePermissionService {

    /**
     *新增权限
     *@param permission
     */
    void savePermission(Permission permission);

    /**
     * 根据权限类型查询对应的集合列表
     * @param permissionTypeMenu
     * @return
     */
    List<Permission> findPermissionListByType(String permissionTypeMenu);

    /**
     * 删除权限
     * @param id
     */
    void delPermission(Integer id);

    /**
     *根据id 修改
     * @param id
     * @return
     */
    Permission findPermissionByid(Integer id);

    /**
     * 修改
     * @param permission
     */
    void permissionEdit(Permission permission);

    /**
     * 查询所有的带权限的角色列表
     * @return roel
     */
    List<Role> findRoleList();

    /**
     * 查看所有权限
     *@return permission
     */
    List<Permission> findAllPermission();

    /**
     * 新增角色
     * @param role
     * @param permissionIds
     */
    void saveRole(Role role, Integer[] permissionIds);

    /**
     * 根据 id 删除角色
     * @param id
     */
    void delRoleById(Integer id);

    /**
     * 根据id查找角色附带角色的权限列表
     * @return role
     */
    Role findRoleWithPermission(Integer id);

    /**
     * 更新角色
     * @param role
     * @param permissionIds
     */
    void editRole(Role role, Integer[] permissionIds);

    /**
     * 在编辑页面判断当前权限的复选框是否被checked
     * @param permissionList 当前角色拥有的权限
     * @return 有顺序的map集合  如果被选择则value为true
     */
    Map<Permission,Boolean> permissionBooleanMap(List<Permission> permissionList);
}
