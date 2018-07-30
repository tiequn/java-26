package com.kaishengit.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.config.Ini;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author guojiabang
 * @date 2018/7/30 0030
 */
public class MyRealmQuickStart {

    private static Logger logger = LoggerFactory.getLogger(MyRealmQuickStart.class);

    public static void main(String[] args) {

        //1. 读取classpath中的shiro.ini配置文件，并创建securityManagerFactory对象
        Factory<SecurityManager> securityManagerFactory = new IniSecurityManagerFactory("classpath:my-realm.ini");
        //2. 获取SecurityManager
        SecurityManager securityManager = securityManagerFactory.getInstance();

        // 3. .设置安全管理器  设置SecurityManager(仅设置一次)
        SecurityUtils.setSecurityManager(securityManager);
        // 4.获取当前登录的对象  通过SecurityUtils的getSubject()获得subject对象（主体账号）
        Subject subject = SecurityUtils.getSubject();

        // 5.判断当前主体有没有被认证（登录）
        if(!subject.isAuthenticated()){

            // 没有通过认证，则根据用户名密码进行登录
            UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken("admin","123123");

            try {
                // 5.通过login方法登录
                subject.login(usernamePasswordToken);
                System.out.println("isAuthenticated:" + subject.isAuthenticated());

                // 方法获得当前登录账号的用户名（默认）
                 subject.getPrincipal();

                logger.info("{} login success", subject.getPrincipal());
            } catch (UnknownAccountException e) {
                logger.info("用户名或者密码错误");
            } catch (IncorrectCredentialsException e) {
                logger.info("密码错误");
            } catch (LockedAccountException e) {
                logger.info("账户被锁定");
            } catch (AuthenticationException ae) {
                logger.info("认证失败");
            }

        }

    }


}
