package com.kaisheng.it.controller;

import com.kaisheng.it.dto.ResponseBean;
import com.kaisheng.it.entity.Employee;
import com.kaisheng.it.entity.FixOrder;
import com.kaisheng.it.service.FixOrderService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;


/**
 * @author guojiabang
 * @date 2018/8/10 0010
 */
@Controller
@RequestMapping("/check")
public class CheckController {

    @Autowired
    private FixOrderService fixOrderService;

    @GetMapping("/list")
    public String checkList(Model model){
        List<FixOrder> fixOrderList = fixOrderService.findFixOrderDetect();
        model.addAttribute("fixOrderList",fixOrderList);
        return "check/list";
    }

    @GetMapping("/{id:\\d+}/receive")
    @ResponseBody
    public ResponseBean receiveTack(@PathVariable Integer id,Model model){

        // 获取当前登录员工对象
        Subject subject = SecurityUtils.getSubject();
        Employee employee = (Employee) subject.getPrincipal();

        try {
            fixOrderService.receiveDetect(id,employee);
        } catch (Exception e) {
            return ResponseBean.error(e.getMessage());
        }
        return ResponseBean.success();
    }

    @GetMapping("/{id:\\d+}/detail")
    public String detail(@PathVariable Integer id, Model model){

        FixOrder fixOrder = fixOrderService.findFixOrderById(id);
        // 获取当前登录的id
        Subject subject = SecurityUtils.getSubject();
        Employee employee = (Employee) subject.getPrincipal();

        model.addAttribute("employeeId",employee.getId());
        model.addAttribute("fixOrder",fixOrder);
        return "/check/detail";
    }

    @GetMapping("/{id:\\d+}/done")
    public String done(@PathVariable Integer id){
        fixOrderService.findDetectOrderById(id);
        return "redirect:/check/list";
    }



}
