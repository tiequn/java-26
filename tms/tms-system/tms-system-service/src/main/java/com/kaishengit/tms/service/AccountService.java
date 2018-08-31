package com.kaishengit.tms.service;

import com.kaishengit.tms.entity.Account;
import com.kaishengit.tms.entity.AccountLoginLog;

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
}
