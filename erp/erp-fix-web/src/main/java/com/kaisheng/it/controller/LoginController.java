package com.kaisheng.it.controller;

import com.kaisheng.it.entity.Employee;
import com.kaisheng.it.service.EmployeeService;
import com.kaisheng.it.service.LoginService;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.util.SavedRequest;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @author guojiabang
 * @date 2018/7/26 0026
 */
@Controller
public class LoginController {

    @Autowired
    private LoginService loginService;

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/home")
    public String home(){

        return "home";
    }

    @GetMapping("/")
    public String login(){

        // 判断当前是否已经通过认证，如果通过则退出登录
        Subject subject = SecurityUtils.getSubject();
        if(subject.isAuthenticated()){
            subject.logout();
        }

        // 记住我返回首页
        if(subject.isRemembered()){
            return "home";
        }

        return "login";
    }

    @PostMapping("/")
    public String login(String employeeTel,
                        String password,
                        String remember,
                        HttpServletRequest req,
                        RedirectAttributes redirectAttributes){

        // 创建subject主体对象
        Subject subject = SecurityUtils.getSubject();
        // 获得登录的IP
        String loginId = req.getRemoteAddr();
        // 通过userTel、password封装UsernamePasswordToken对象进行登录 remeber != null 判断记住我是否打勾
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(employeeTel,DigestUtils.md5Hex(password), remember != null,loginId);

        try {

            // 登录
            subject.login(usernamePasswordToken);

            if(subject.hasRole("fix:service")){

                Employee employee = employeeService.findEmployeeByTel(employeeTel);
                Session session = subject.getSession();
                session.setAttribute("employee",employee);

                // 判断跳转路径  跳转到登录前的请求页面
                SavedRequest savedRequest = WebUtils.getSavedRequest(req);
                String url = "/home";
                if(savedRequest != null){
                    // 获得callback的url
                    url = savedRequest.getRequestUrl();
                }
                return "redirect:" + url;
            } else {
                redirectAttributes.addFlashAttribute("message","您没有访问权限");
            }

        }catch (UnknownAccountException |IncorrectCredentialsException e) {
            redirectAttributes.addFlashAttribute("message", "用户名或者密码错误");
        } catch (LockedAccountException e) {
            redirectAttributes.addFlashAttribute("message", e.getMessage());
        } catch (AuthenticationException e) {
            redirectAttributes.addFlashAttribute("message", "登录失败");
        }
        return "redirect:/";

    }

   @GetMapping("/401")
   public String unauthorizedUrl(){

        return "error/401";
    }


}
