package com.kaisheng.it.service;

import com.kaisheng.it.entity.Employee;
import com.kaisheng.it.exception.ServiceException;

/**
 * @author guojiabang
 * @date 2018/7/26 0026
 */
public interface LoginService {
    /**
     *  员工登录
     * @param employeeTel  用户手机号号码
     * @param password 用户密码
     * @param loginIP   用户登录ip
     * @return employee 对象
     *
     *
     */
    Employee login(String employeeTel, String password, String loginIP)throws ServiceException;
}
