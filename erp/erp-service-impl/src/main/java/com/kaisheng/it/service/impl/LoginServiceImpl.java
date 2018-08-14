package com.kaisheng.it.service.impl;

import com.kaisheng.it.entity.Employee;
import com.kaisheng.it.entity.EmployeeExample;
import com.kaisheng.it.entity.EmployeeLoginLog;
import com.kaisheng.it.exception.ServiceException;
import com.kaisheng.it.mapper.EmployeeLoginLogMapper;
import com.kaisheng.it.mapper.EmployeeMapper;
import com.kaisheng.it.service.LoginService;
import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author guojiabang
 * @date 2018/7/26 0026
 */
public class LoginServiceImpl implements LoginService {

    Logger logger = LoggerFactory.getLogger(LoginServiceImpl.class);

    @Autowired
    private EmployeeMapper employeeMapper;

    @Autowired
    private EmployeeLoginLogMapper employeeLoginLogMapper;

    /**
     * 员工登录
     * @param employeeTel  用户手机号号码
     * @param password 用户密码
     * @param loginIP  用户登录ip
     * @return employee 对象
     *
     */
    @Override
    public Employee login(String employeeTel, String password, String loginIP) throws ServiceException {

        System.out.println(DigestUtils.md5Hex("000000"));
        // 根据userTel获得对应的employee对象
        EmployeeExample employeeExample = new EmployeeExample();
        employeeExample.createCriteria().andEmployeeTelEqualTo(employeeTel);

        List<Employee> employeeList = employeeMapper.selectByExample(employeeExample);

        Employee employee = null;
        /* employeeList.size() > 0 */
        if(employeeList != null && !employeeList.isEmpty()){
            employee = employeeList.get(0);
            // 判断登录密码是否匹配
            if(employee.getPassword().equals(DigestUtils.md5Hex(password))){
                // 判断员工状态
                if(employee.getState().equals(Employee.EMPLOYEE_STATE_NORMAL)) {

                    // 记录登录日志
                    EmployeeLoginLog employeeLoginLog = new EmployeeLoginLog();
                    // 登录ip
                    employeeLoginLog.setLoginIp(loginIP);
                    // 登录时间
                    employeeLoginLog.setLoginTime(new Date());
                    // 登录对象的id
                    employeeLoginLog.setEmployeeId(employee.getId());

                    employeeLoginLogMapper.insertSelective(employeeLoginLog);

                    logger.info("{}{} 在 {} 登录了系统", employee.getEmployeeName(), employee.getEmployeeTel(), new Date());

                } else {
                    throw new ServiceException("当前账户状态异常，请联系管理员");
                }
            }else {
                throw new ServiceException("用户名或者密码错误");
            }
        } else {
            throw new ServiceException("用户名或者密码错误");
        }

        return null;
    }



}
