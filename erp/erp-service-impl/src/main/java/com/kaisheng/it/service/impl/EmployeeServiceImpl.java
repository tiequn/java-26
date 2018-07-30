package com.kaisheng.it.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kaisheng.it.entity.*;
import com.kaisheng.it.mapper.EmployeeLoginLogMapper;
import com.kaisheng.it.mapper.EmployeeMapper;
import com.kaisheng.it.mapper.EmployeeRoleMapper;
import com.kaisheng.it.mapper.RoleMapper;
import com.kaisheng.it.service.EmployeeService;
import com.kaisheng.it.util.Constant;
import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * @author guojiabang
 * @date 2018/7/29 0029
 */
@Service
public class EmployeeServiceImpl implements EmployeeService {

    Logger logger = LoggerFactory.getLogger(EmployeeServiceImpl.class);

    @Autowired
    private EmployeeMapper employeeMapper;

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private EmployeeRoleMapper employeeRoleMapper;

    @Autowired
    private EmployeeLoginLogMapper employeeLoginLogMapper;

    /**
     * 获得角色多有的角色的列表
     * @return
     */
    @Override
    public List<Role> findAllRole() {

        RoleExample roleExample = new RoleExample();
        return roleMapper.selectByExample(roleExample);
    }

    /**
     * 新增员工
     * @param employee
     * @param roleIds
     */
    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public void saveEmployee(Employee employee, Integer[] roleIds) {
        //对密码进行MD5加密
        String codePassword = DigestUtils.md5Hex(employee.getPassword());
        employee.setPassword(codePassword);

        //账号默认状态
        employee.setState(Employee.EMPLOYEE_STATE_NORMAL);

        //添加账号和角色的对应关系表
        employeeMapper.insertSelective(employee);

        for(Integer roleId : roleIds) {
            EmployeeRole employeeRole = new EmployeeRole();
            employeeRole.setEmployeeId(employee.getId());
            employeeRole.setRoleId(roleId);

            employeeRoleMapper.insertSelective(employeeRole);
        }
        logger.info("新增账号 {}", employee);
    }

    /**
     * 查找所有账户的角色
     *
     * @param resultMap
     * @return
     */
    @Override
    public PageInfo<Employee> findAllAccountWithRolesByResutMap(Map<String, Object> resultMap, Integer pageNo) {

        // 分页
        PageHelper.startPage(pageNo, Constant.DEFAULT_PAGE_SIZE);

        List<Employee> employeeList = employeeMapper.findAllAccountWithRolesByResultMap(resultMap);
        PageInfo<Employee> employeePageInfo = new PageInfo<>(employeeList);

        return employeePageInfo;

    }

    /**
     * 删除员工
     *
     * @param id
     */
    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public void delEmployee(Integer id) {

        EmployeeRoleExample employeeRoleExample = new EmployeeRoleExample();
        employeeRoleExample.createCriteria().andEmployeeIdEqualTo(id);

        employeeRoleMapper.deleteByExample(employeeRoleExample);

        Employee employee = employeeMapper.selectByPrimaryKey(id);
        if(employee != null){
            employeeMapper.deleteByPrimaryKey(id);
            logger.info("删除员工:{}", employee);
        }
    }

    /**
     * 根据id查询账号信息
     * @param id
     * @return
     */
    @Override
    public Employee findEmployeeById(Integer id) {
        return employeeMapper.selectByPrimaryKey(id);
    }

    /**
     * 根据id查询当前账号拥有的角色列表
     *
     * @param id
     * @return
     */
    @Override
    public List<Role> findRoleListByEmployeeId(Integer id) {

        return roleMapper.findRoleListByemployeeId(id);
    }

    /**
     * 修改员工账号及对象
     *
     * @param employee
     * @param roleIds
     */
    @Override
    public void findEditByEmployee(Employee employee, Integer[] roleIds) {

        // 删除员工和角色的关联关系表
        EmployeeRoleExample employeeRoleExample = new EmployeeRoleExample();
        employeeRoleExample.createCriteria().andEmployeeIdEqualTo(employee.getId());
        employeeRoleMapper.deleteByExample(employeeRoleExample);

        // 重建关联关系表
        for (Integer roleId : roleIds ){
            EmployeeRole employeeRole = new EmployeeRole();
            employeeRole.setEmployeeId(employee.getId());
            employeeRole.setRoleId(roleId);

            employeeRoleMapper.insert(employeeRole);
        }

        employeeMapper.updateByPrimaryKeySelective(employee);
        logger.info("修改员工:{}", employee);

    }

    /**
     * 修改状态
     * @param employee
     */
    @Override
    public void updateSate(Employee employee) {

        employeeMapper.updateByPrimaryKeySelective(employee);
    }

    /**
     * 根据电话号码查询账号对象
     *
     * @param userTel
     * @return
     */
    @Override
    public Employee findEmployeeByTel(String userTel) {

        EmployeeExample employeeExample = new EmployeeExample();
        employeeExample.createCriteria().andEmployeeTelEqualTo(userTel);

        List<Employee> employeeList = employeeMapper.selectByExample(employeeExample);
        if(employeeList != null && !employeeList.isEmpty()){
            return employeeList.get(0);
        }
        return null;
    }

    /**
     * 记录登录日志
     *
     * @param employeeLoginLog
     */
    @Override
    public void saveLoginLog(EmployeeLoginLog employeeLoginLog) {
        employeeLoginLogMapper.insertSelective(employeeLoginLog);
    }

}
