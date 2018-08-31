package com.kaishengit.tms.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.kaishengit.tms.entity.Account;
import com.kaishengit.tms.entity.AccountExample;
import com.kaishengit.tms.entity.AccountLoginLog;
import com.kaishengit.tms.mapper.AccountLoginLogMapper;
import com.kaishengit.tms.mapper.AccountMapper;
import com.kaishengit.tms.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * 系统账号的业务类
 * @author guojiabang
 * @date 2018/8/31 0031
 */
@Service(version = "1.0", timeout = 5000)
public class AccountServiceIMpl implements AccountService {

    @Autowired
    private AccountMapper accountMapper;

    @Autowired
    private AccountLoginLogMapper accountLoginLogMapper;

    /**
     * 根据手机号码查询Account对象
     * @param userName
     * @return
     */
    @Override
    public Account findByUserName(String userName) {

        AccountExample accountExample = new AccountExample();
        accountExample.createCriteria().andAccountMobileEqualTo(userName);
        List<Account> accountList = accountMapper.selectByExample(accountExample);

        if(accountList != null && !accountList.isEmpty()){
            return accountList.get(0);
        }
        return null;
    }

    /**
     * 新增Account登录日志
     * @param accountLoginLog 登录日志
     */
    @Override
    public void saveAccountLoginLog(AccountLoginLog accountLoginLog) {
       accountLoginLogMapper.insertSelective(accountLoginLog);

    }
}
