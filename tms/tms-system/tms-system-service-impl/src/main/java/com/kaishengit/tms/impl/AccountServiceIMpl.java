package com.kaishengit.tms.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.kaishengit.tms.entity.Account;
import com.kaishengit.tms.entity.AccountExample;
import com.kaishengit.tms.entity.AccountLoginLog;
import com.kaishengit.tms.entity.AccountRolesKey;
import com.kaishengit.tms.mapper.AccountLoginLogMapper;
import com.kaishengit.tms.mapper.AccountMapper;
import com.kaishengit.tms.mapper.AccountRolesMapper;
import com.kaishengit.tms.service.AccountService;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;


import java.util.Date;
import java.util.List;
import java.util.Map;

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

    @Autowired
    private AccountRolesMapper accountRolesMapper;

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

    /**
     * 根据UI传来的查询参数查询所有账号并加载对应的角色列表
     *
     * @param requestParam
     * @return
     */
    @Override
    public List<Account> findAllAccountWithRolesByQueryParam(Map<String, Object> requestParam) {
        return accountMapper.findAllWithRolesByQueryParam(requestParam);
    }

    /**
     * 新增账号
     * @param account
     * @param rolesIds
     */
    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public void saveAccount(Account account, Integer[] rolesIds) {
        account.setCreateTime(new Date());
        // 账号默认密码为手机号码的后6位
        String password;
        if(account.getAccountMobile().length() <= 6){
            password = account.getAccountMobile();
        } else{
            password = account.getAccountMobile().substring(6);
        }
        // 对密码进行MD5加密
        password = DigestUtils.md5Hex(password);
        account.setAccountPassword(password);

        // 账号默认状态
        account.setAccountState(Account.STATE_NORMAL);

        accountMapper.insertSelective(account);

        //添加账号和角色的对应关系表
        for (Integer roleId : rolesIds){
            AccountRolesKey accountRolesKey = new AccountRolesKey();
            accountRolesKey.setAccountId(account.getId());
            accountRolesKey.setRolesId(roleId);

            accountRolesMapper.insert(accountRolesKey);

        }


    }
}
