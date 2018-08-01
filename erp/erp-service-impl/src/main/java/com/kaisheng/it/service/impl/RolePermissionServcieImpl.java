package com.kaisheng.it.service.impl;

import com.google.common.base.Predicate;
import com.google.common.collect.Collections2;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.kaisheng.it.entity.*;
import com.kaisheng.it.exception.ServiceException;
import com.kaisheng.it.mapper.EmployeeRoleMapper;
import com.kaisheng.it.mapper.PermissionMapper;
import com.kaisheng.it.mapper.RoleMapper;
import com.kaisheng.it.mapper.RolePermissionMapper;
import com.kaisheng.it.service.RolePermissionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author guojiabang
 * @date 2018/7/27 0027
 */
@Service
public class RolePermissionServcieImpl implements RolePermissionService {

    Logger logger = LoggerFactory.getLogger(RolePermissionServcieImpl.class);

    @Autowired
    private PermissionMapper permissionMapper;

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private RolePermissionMapper rolePermissionMapper;

    @Autowired
    private EmployeeRoleMapper employeeRoleMapper;



    /**
     * 新增权限
     * @param permission
     */
    @Override
    public void savePermission(Permission permission) {

        permissionMapper.insertSelective(permission);

    }

    /**
     * 根据权限类型查询对应的集合列表
     *
     * @param permissionTypeMenu
     * @return
     */
    @Override
    public List<Permission> findPermissionListByType(String permissionTypeMenu) {

       PermissionExample permissionExample = new PermissionExample();
       permissionExample.createCriteria().andPermissionTypeEqualTo(permissionTypeMenu);

        return permissionMapper.selectByExample(permissionExample);
    }

    /**
     * 删除权限
     * @param id
     */
    @Override
    public void delPermission(Integer id) throws ServiceException {

        // 1.如果是父权限，并且拥有子权限，则不能删除
        PermissionExample permissionExample = new PermissionExample();
        permissionExample.createCriteria().andPidEqualTo(id);
        List<Permission> permissionList = permissionMapper.selectByExample(permissionExample);

        if(permissionList != null && permissionList.size() > 0){
            throw new ServiceException("该权限下有子权限，不能删除");
        }

        // 2.如果该权限被角色所使用不能删除
        RolePermissionExample rolePermissionExample = new RolePermissionExample();
        rolePermissionExample.createCriteria().andPermissionIdEqualTo(id);

        List<RolePermission> rolePermissionList = rolePermissionMapper.selectByExample(rolePermissionExample);

        if(rolePermissionList != null && rolePermissionList.size() > 0){
            throw new ServiceException("该权限已被使用，不能删除");
        }
        permissionMapper.deleteByPrimaryKey(id);
    }

    /**
     * 根据id 修改
     * @param id
     * @return
     */
    @Override
    public Permission findPermissionByid(Integer id) {

        Permission permission = permissionMapper.selectByPrimaryKey(id);
        return permission;
    }

    /**
     * 修改
     *
     * @param permission
     */
    @Override
    public void permissionEdit(Permission permission) {
        if(permission.getPermissionType().equals(Permission.PERMISSION_TYPE_MENU)){
            permission.setPermissionType(Permission.PERMISSION_TYPE_BUTTON);
        } else {
            permission.setPermissionType(Permission.PERMISSION_TYPE_MENU);
        }

        permissionMapper.updateByPrimaryKeySelective(permission);

        logger.debug("修改权限: {}", permission);
    }

    /**
     * 查询所有的带权限的角色列表
     * @return
     */
    @Override
    public List<Role> findRoleList() {

        List<Role> roleList = roleMapper.findRoleListByPermission();
        return roleList;
    }

    /**
     * 查看所有权限
     * @return
     */
    @Override
    public List<Permission> findAllPermission() {
        PermissionExample permissionExample = new PermissionExample();

        List<Permission> permissionList = permissionMapper.selectByExample(permissionExample);

        List<Permission> endList = new ArrayList<>();
        treeList(permissionList,endList,0);

        return endList;
    }

    /**
     * 将查询数据库的角色列表转换为树形集合结果
     * @param sourceList  数据库查询出的集合
     * @param endList     转换结束的结果集合
     * @param parentId    父ID
     */
    public void treeList(List<Permission> sourceList, List<Permission> endList, int parentId){
        List<Permission> tempList = Lists.newArrayList(Collections2.filter(sourceList, new Predicate<Permission>() {
            @Override
            public boolean apply(@Nullable Permission permission) {
               return permission.getPid().equals(parentId);
            }
        }));

        for(Permission permission : tempList){
            endList.add(permission);
            treeList(sourceList,endList,permission.getId());
        }
    }

    /**
     * 新增角色
     *
     * @param role
     * @param permissionIds
     */
    @Override
    @Transactional(rollbackFor = RuntimeException.class )
    public void saveRole(Role role, Integer[] permissionIds) {
        // 新增角色
        roleMapper.insertSelective(role);

        // 新增角色权限关联关系
        for (Integer permissionId : permissionIds){
            RolePermission rolePermission = new RolePermission();
            rolePermission.setRoleId(role.getId());
            rolePermission.setPermissionId(permissionId);

            rolePermissionMapper.insertSelective(rolePermission);
        }
    }

    /**
     * 根据 id 删除角色
     * @param id
     */
    @Override
    public void delRoleById(Integer id) {
        //查询是否被账号引用，如果引用则不能删除
        EmployeeRoleExample employeeRoleExample = new EmployeeRoleExample();
        employeeRoleExample.createCriteria().andEmployeeIdEqualTo(id);

        List<EmployeeRole> employeeRoleList = employeeRoleMapper.selectByExample(employeeRoleExample);

        if(employeeRoleList != null && !employeeRoleList.isEmpty()){
            throw  new ServiceException("该角色以被引用，不能删除");
        }

        //删除角色和权限的关系记录
        RolePermissionExample rolePermissionExample = new RolePermissionExample();
        rolePermissionExample.createCriteria().andRoleIdEqualTo(id);

        rolePermissionMapper.deleteByExample(rolePermissionExample);

        //删除角色
        Role role = roleMapper.selectByPrimaryKey(id);
        if(role != null){
            roleMapper.deleteByPrimaryKey(id);
            logger.info("删除角色:{}",role);
        }
    }

    /**
     * 根据id查找角色附带角色的权限列表
     * @param id
     * @return role
     */
    @Override
    public Role findRoleWithPermission(Integer id) {

        Role role = roleMapper.fingRoleByIdwWithermissionP(id);

        return role;
    }

    /**
     * 更新角色
     * @param role
     * @param permissionIds
     */
    @Override
    public void editRole(Role role, Integer[] permissionIds) {

        //将角色原有和权限的对应关系删除
        RolePermissionExample rolePermissionExample = new RolePermissionExample();
        rolePermissionExample.createCriteria().andRoleIdEqualTo(role.getId());
        rolePermissionMapper.deleteByExample(rolePermissionExample);

        //重建角色和权限的对应关系
        for (Integer permissionId : permissionIds){
            RolePermission rolePermission = new RolePermission();
            rolePermission.setPermissionId(permissionId);
            rolePermission.setRoleId(role.getId());

            rolePermissionMapper.insertSelective(rolePermission);
        }

        //修改角色对象
        roleMapper.updateByPrimaryKeySelective(role);

        logger.info("修改角色:{}", role);
    }

    /**
     * 在编辑页面判断当前权限的复选框是否被checked
     * @param permissionList 当前角色拥有的权限
     * @return 有顺序的map集合  如果被选择则value为true
     */
    @Override
    public Map<Permission, Boolean> permissionBooleanMap(List<Permission> permissionList) {

        // 获得所有的权限列表
        List<Permission> permissionLists = findAllPermission();

        // 获得有序的map集合
        Map<Permission, Boolean> resultMap = Maps.newLinkedHashMap();

        for (Permission permission : permissionLists){
            Boolean flag = false;
            for (Permission rolePermission : permissionList){
                if(permission.getId().equals(rolePermission.getId())){
                    flag = true;

                    break;
                }
            }
            resultMap.put(permission,flag);
        }
        return resultMap;
    }

    /**
     * 根据对象ID查询所有的对应的角色
     *
     * @param id
     * @return
     */
    @Override
    public List<Role> findRoleByAccountId(Integer id) {
        return roleMapper.findAllByAccountId(id);
    }

    /**
     * 根据roleId查询对应的权限
     *
     * @param id
     * @return
     */
    @Override
    public List<Permission> findAllPermissionByRoleById(Integer id) {
        return permissionMapper.findAllByRoleId(id);
    }


}
