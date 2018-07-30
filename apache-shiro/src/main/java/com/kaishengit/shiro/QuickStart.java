package com.kaishengit.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;

/**
 * @author guojiabang
 * @date 2018/7/30 0030
 */
public class QuickStart {

    private static Logger logger = LoggerFactory.getLogger(QuickStart.class);

    public static void main(String[] args) {

        //1. 读取classpath中的shiro.ini配置文件，并创建securityManagerFactory对象
        Factory<SecurityManager> securityManagerFactory  = new IniSecurityManagerFactory("classpath:shiro-realm.ini");
        //2. 获取SecurityManager
        SecurityManager securityManager =  securityManagerFactory.getInstance();
        // 3. 设置SecurityManager(仅设置一次)
        SecurityUtils.setSecurityManager(securityManager);

        // 4.获取当前登录的对象  通过SecurityUtils的getSubject()获得subject对象（主体账号）
        Subject subject = SecurityUtils.getSubject();

        // 5.判断当前主体有没有被认证（登录）
        if(!subject.isAuthenticated()) {

            // 6.没有通过认证，则根据用户名密码进行登录
            UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken("admin", "123123");

            try {
                // 7.通过login方法登录
                subject.login(usernamePasswordToken);

                //System.out.println("isAuthenticated:" + subject.isAuthenticated());
                // subject.getPrincipal()方法获得当前登录账号的用户名（默认）

                // subject.getPrincipal()方法获得当前登录账号的用户名（默认）
                logger.info("{} login success", subject.getPrincipal());

                // 通过hasRole("角色名称")判断当前主体是否具有某个角色
                logger.info("hashRole {} : {}" ,"admin", subject.hasRole("admin"));
                logger.info("hashRole {} : {}" ,"admin", subject.hasRole("user"));

                // 通过checkRole()方法来判断当前主体是否拥有某权限，如果没有则抛出异常
                // subject.checkRole("user");

                // 通过hasRoles()方法判断subject主体对多个角色的拥有权
                boolean[] results = subject.hasRoles(Arrays.asList("admin","user","tom"));
                for (Boolean result : results){
                    logger.info("result:{}",result);
                }

                // 判断当前登录对象是否拥有所有角色
                logger.info("result:{}", subject.hasAllRoles(Arrays.asList("admin")));

                // 通过isPermitted()判断Shiro-realm.ini当前对象是否具有对应的权限
                logger.info("customer:add -->{}",subject.isPermitted("customer:delete"));
                logger.info("customer:* -->{}",subject.isPermitted("customer:delete","customer:query"));
                logger.info("customer:all -->{}",subject.isPermittedAll("customer:add","customer:query"));

                // 判断是否具有该权限，如果没有则抛出异常
                // subject.checkPermission("customer:delete");

                // 使用session存值取值，大多数情况下可取代HttpSession
                Session session = subject.getSession();
                // 存值
                session.setAttribute("username", subject.getPrincipal());
                // 取值
                logger.info("username :{}", session.getAttribute("username"));


                // 安全退出
                subject.logout();

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
