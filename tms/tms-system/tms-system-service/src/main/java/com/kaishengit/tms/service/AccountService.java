package com.kaishengit.tms.service;

import com.kaishengit.tms.entity.Account;
import com.kaishengit.tms.entity.AccountLoginLog;

import java.util.List;
import java.util.Map;

/**
 * 系统账号的业务类
 * @author guojiabang
 * @date 2018/8/31 0031
 */
public interface AccountService {

    /**
     *根据手机号码查询Account对象
     * @param userName
     * @return
     */
    Account findByUserName(String userName);

    /**
     * 新增Account登录日志
     * @param accountLoginLog
     */
    void saveAccountLoginLog(AccountLoginLog accountLoginLog);

    /**
     * 根据UI传来的查询参数查询所有账号并加载对应的角色列表
     * @param requestParam
     * @return
     */
    List<Account> findAllAccountWithRolesByQueryParam(Map<String, Object> requestParam);

    /**
     * 新增账号
     * @param account
     * @param rolesIds
     */
    void saveAccount(Account account, Integer[] rolesIds);
}
