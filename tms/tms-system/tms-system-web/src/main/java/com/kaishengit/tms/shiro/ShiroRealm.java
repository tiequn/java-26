package com.kaishengit.tms.shiro;

import com.alibaba.dubbo.config.annotation.Reference;
import com.kaishengit.tms.entity.Account;
import com.kaishengit.tms.entity.AccountLoginLog;
import com.kaishengit.tms.service.AccountService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

/**
 * @author guojiabang
 * @date 2018/8/31 0031
 */
public class ShiroRealm extends AuthorizingRealm {

    private Logger logger = LoggerFactory.getLogger(ShiroRealm.class);

    @Reference(version = "1.0")
    private AccountService accountService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }

    /**
     * 判断登录
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        // 将 authenticationToken 转化为 UsernamePasswordToken
        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) authenticationToken;
        // 获的username
        String userName = usernamePasswordToken.getUsername();
        if(userName != null){
            Account account = accountService.findByUserName(userName);
            if(account == null){
                throw new UnknownAccountException("找不到该账号:" + userName);
            } else{
                // 判断状态是否正常
                if(Account.STATE_NORMAL.equals(account.getAccountState())){
                    logger.info("{} 登录成功: {}",account,usernamePasswordToken.getHost());

                    //保存登录日志
                    AccountLoginLog accountLoginLog = new AccountLoginLog();
                    accountLoginLog.setLoginTime(new Date());
                    accountLoginLog.setAccountId(account.getId());
                    accountLoginLog.setLoginIp(usernamePasswordToken.getHost());

                    accountService.saveAccountLoginLog(accountLoginLog);

                    return new SimpleAuthenticationInfo(account,account.getAccountPassword(),getName());
                } else{
                    throw new LockedAccountException("账号被禁用或锁定:" + account.getAccountState());
                }

            }
        }
        return null;
    }
}
