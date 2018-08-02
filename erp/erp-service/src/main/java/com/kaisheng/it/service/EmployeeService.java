package com.kaisheng.it.service;

import com.github.pagehelper.PageInfo;
import com.kaisheng.it.entity.Employee;
import com.kaisheng.it.entity.EmployeeLoginLog;
import com.kaisheng.it.entity.Role;

import java.util.List;
import java.util.Map;

/**
 * @author guojiabang
 * @date 2018/7/29 0029
 */
public interface EmployeeService {
    /**
     * 获得角色多有的角色的列表
     * @return
     */
    List<Role> findAllRole();

    /**
     * 新增员工
     * @param employee
     * @param roleIds
     */
    void saveEmployee(Employee employee, Integer[] roleIds) throws SecurityException;

    /**
     * 查找所有账户的角色
     * @param resultMap
     * @return
     */
    PageInfo<Employee> findAllAccountWithRolesByResutMap(Map<String,Object> resultMap, Integer pageNo);

    /**
     * 删除员工
     * @param id
     */
    void delEmployee(Integer id);

    /**
     *  根据id查询账号信息
     * @param id
     * @return
     */
    Employee findEmployeeById(Integer id);

    /**
     * 根据id查询当前账号拥有的角色列表
     * @param id
     * @return
     */
    List<Role> findRoleListByEmployeeId(Integer id);

    /**
     * 修改员工账号及角色对象
     * @param employee
     * @param roleIds
     */
    void findEditByEmployee(Employee employee, Integer[] roleIds);

    /**
     * 修改状态
     * @param employee
     */
    void updateSate(Employee employee);

    /**
     * 根据电话号码查询账号对象
     * @param employeeTel 对象电话
     * @return
     */
    Employee findEmployeeByTel(String employeeTel);

    /**
     * 记录登录日志
     * @param employeeLoginLog
     */
    void saveLoginLog(EmployeeLoginLog employeeLoginLog);
}
