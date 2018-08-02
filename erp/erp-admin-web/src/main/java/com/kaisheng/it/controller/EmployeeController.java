package com.kaisheng.it.controller;

import com.github.pagehelper.PageInfo;
import com.google.common.collect.Maps;
import com.kaisheng.it.dto.ResponseBean;
import com.kaisheng.it.entity.Employee;
import com.kaisheng.it.entity.Role;
import com.kaisheng.it.exception.ServiceException;
import com.kaisheng.it.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Map;

/**
 * @author guojiabang
 * @date 2018/7/29 0029
 */
@Controller
@RequestMapping("/manage/account")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping
    public String home(
                       @RequestParam(name = "p", defaultValue = "1") Integer pageNo,
                       @RequestParam(required = false) Integer roleId,
                       @RequestParam(required = false) String nameMobile,
                       Model model){

        Map<String, Object> resultMap = Maps.newHashMap();
        resultMap.put("roleId",roleId);
        resultMap.put("nameMobile", nameMobile);

        PageInfo<Employee> pageInfo = employeeService.findAllAccountWithRolesByResutMap(resultMap,pageNo);
        List<Role> roleList = employeeService.findAllRole();

        model.addAttribute("pageInfo", pageInfo);
        model.addAttribute("roleList",roleList);
        return "manage/account/home";
    }

    @GetMapping("/add")
    public String addEmployee(Model model){

        List<Role> roleList =employeeService.findAllRole();

        model.addAttribute("roleList", roleList);
        return "/manage/account/add";
    }

    @PostMapping("/add")
    public String addEmployee(Employee employee, Integer[] roleIds, RedirectAttributes redirectAttributes){
        try {
            employeeService.saveEmployee(employee,roleIds);
            redirectAttributes.addFlashAttribute("message","添加成功");
        } catch (ServiceException e) {
            redirectAttributes.addFlashAttribute("message",e.getMessage());
        }
        return "redirect:/manage/account";
    }

    @GetMapping("/{id:\\d+}/del")
    @ResponseBody
    public ResponseBean delEmployee(@PathVariable Integer id){

        try {
            employeeService.delEmployee(id);
        } catch (Exception e) {
            return ResponseBean.error(e.getMessage());
        }
        return ResponseBean.success();
    }

    @GetMapping("/{id:\\d+}/edit")
    public String editEmployee(@PathVariable Integer id, Model model){

        // 封装账号信息
        Employee employee = employeeService.findEmployeeById(id);

        // 封装所有角色列表
        List<Role> roleList = employeeService.findAllRole();

        // 封装当前账号拥有的角色列表
        List<Role> employeeRoleList = employeeService.findRoleListByEmployeeId(id);

        model.addAttribute("employee", employee);
        model.addAttribute("roleList", roleList);
        model.addAttribute("employeeRoleList",employeeRoleList);

        return "manage/account/edit";
    }

    @PostMapping("/{id:\\d+}/edit")
    public String editEmployee(Employee employee, Integer[] roleIds, RedirectAttributes redirectAttributes){

        employeeService.findEditByEmployee(employee,roleIds);

        redirectAttributes.addFlashAttribute("message","修改成功");
        return "redirect:/manage/account";
    }

    @GetMapping("/{id:\\d+}/error")
    public String errorEmployee(@PathVariable Integer id, RedirectAttributes redirectAttributes){

        Employee employee = employeeService.findEmployeeById(id);

        employee.setState(Employee.EMPLOYEE_STATE_FROZEN);

        employeeService.updateSate(employee);

        redirectAttributes.addFlashAttribute("message","状态已禁用");
        return "redirect:/manage/account";
    }

    @GetMapping("/{id:\\d+}/success")
    public String successEmployee(@PathVariable Integer id, RedirectAttributes redirectAttributes){

        Employee employee = employeeService.findEmployeeById(id);

        employee.setState(Employee.EMPLOYEE_STATE_NORMAL);

        employeeService.updateSate(employee);

        redirectAttributes.addFlashAttribute("message","状态已正常");
        return "redirect:/manage/account";
    }


    @GetMapping("/profile")
    public String profile(){

        return "manage/account/profile";
    }

    @PostMapping("/profile")
    public String profile(Employee employee){

        return "redirect:/manage/account";
    }

}
