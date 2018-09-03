package com.kaishengit.tms.controller;

import com.kaishengit.tms.dto.ResponseBean;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.util.SavedRequest;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * 首页及登录、登出的控制器
 * @author guojiabang
 * @date 2018/9/1 0001
 */
@Controller
public class HomeController {

    /**
     * 登录页面
     * @return
     */
    @GetMapping("/")
    public String index(){
        Subject subject = SecurityUtils.getSubject();

        System.out.println("isAuthenticated:" + subject.isAuthenticated());
        System.out.println("isPermitted" + subject.isRemembered());

        //判断当前是否有已经认证的账号，如果有，则退出该账号
        if(subject.isAuthenticated()){
            subject.logout();
        }
        if(subject.isRemembered()){
            //如果当前为被记住（通过rememberMe认证），则直接跳转到登录成功页面 home
            return "redirect:/home";
        }
        return "index";
    }

    /**
     * 登录系统
     * @param userName
     * @param password
     * @param rememberMe
     * @param request
     * @return
     */
    @PostMapping("/")
    @ResponseBody
    public ResponseBean login(String userName,
                              String password,
                              String rememberMe,
                              HttpServletRequest request ){
        // 创建Subject对象
        Subject subject = SecurityUtils.getSubject();
        // 根据账号和密码进行登录
        // 获取登录的ip
        String requestId = request.getRemoteAddr();
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(userName,DigestUtils.md5Hex(password),rememberMe != null, requestId);

        try {
            subject.login(usernamePasswordToken);

            //登录后跳转目标的判断
            SavedRequest savedRequest = WebUtils.getSavedRequest(request);
            String url = "/home";
            if(savedRequest != null){
                url = savedRequest.getRequestUrl();
            }
            return ResponseBean.success(url);
        } catch (UnknownAccountException | IncorrectCredentialsException ex) {
            ex.printStackTrace();
            return ResponseBean.error("账号或密码错误");
        } catch (LockedAccountException ex) {
            ex.printStackTrace();
            return ResponseBean.error("账号被锁定");
        } catch (AuthenticationException ex) {
            ex.printStackTrace();
            return ResponseBean.error("账号或密码错误");
        }
    }

    /**
     * 登录成功页面
     * @return
     */
    @GetMapping("/home")
    public String home(){
        return "/home";
    }

    /**
     * 没有权限页面
     * @return
     */
    @GetMapping("/401")
    public String unauthorizedUrl(){
        return "/error/401";
    }

}
