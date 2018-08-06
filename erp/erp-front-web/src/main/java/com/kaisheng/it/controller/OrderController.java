package com.kaisheng.it.controller;

import com.github.pagehelper.PageInfo;
import com.google.gson.Gson;
import com.kaisheng.it.dto.ResponseBean;
import com.kaisheng.it.entity.*;
import com.kaisheng.it.service.OrderService;
import com.kaisheng.it.service.PartsService;
import com.kaisheng.it.vo.OrderVo;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @author guojiabang
 * @date 2018/8/2 0002
 */
@Controller
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private PartsService partsService;


    @GetMapping("/undone/list")
    public String undoneList(@RequestParam(name = "p", defaultValue = "1", required = false) Integer pageNo,
                             @RequestParam(required = false) String licenceNo,
                             @RequestParam(required = false) String tel,
                             @RequestParam(required = false) String startTime,
                             @RequestParam(required = false) String endTime,
                             Model model
                             ){
        // 封装筛选querMap集合
        Map<String,Object> queryMap = new HashMap<>();
        queryMap.put("pageNo", pageNo);
        queryMap.put("licenceNo", licenceNo);
        queryMap.put("tel", tel);
        queryMap.put("startTime", startTime);
        queryMap.put("endTime", endTime);
        queryMap.put("exState", Order.ORDER_STATE_DONE);

        PageInfo<Order> page = orderService.findPageByParam(queryMap);

        model.addAttribute("type","");
        model.addAttribute("page",page);
        return "order/list";
    }

    @GetMapping("/done/list")
    public String doneList(@RequestParam(name = "p", defaultValue = "1", required = false) Integer pageNo,
                           @RequestParam(required = false) String licenceNo,
                           @RequestParam(required = false) String tel,
                           @RequestParam(required = false) String startTime,
                           @RequestParam(required = false) String endTime,
                           Model model
    ){
        // 封装筛选querMap集合
        Map<String,Object> queryMap = new HashMap<>();
        queryMap.put("pageNo", pageNo);
        queryMap.put("licenceNo", licenceNo);
        queryMap.put("tel", tel);
        queryMap.put("startTime", startTime);
        queryMap.put("endTime", endTime);
        queryMap.put("state", Order.ORDER_STATE_DONE);

        PageInfo<Order> page = orderService.findPageByParam(queryMap);

        model.addAttribute("type","done");
        model.addAttribute("page",page);
        return "order/hisList";

    }

    @GetMapping("/new")
    public String newOrder(){

        return "order/new";
    }

    @PostMapping("/new")
    @ResponseBody
    public ResponseBean newOrder(String json){
        // 将前端数据转化成对对象
        Gson gson = new Gson();
        OrderVo orderVo = gson.fromJson(json,OrderVo.class);
        // 获得当前登录的员工对象
        Subject subject = SecurityUtils.getSubject();
        Employee employee = (Employee) subject.getPrincipal();

        orderService.saveOrder(orderVo,employee.getId());

        return ResponseBean.success();
    }

    @GetMapping("/service/types")
    @ResponseBody
    public ResponseBean serviceTypes(){
       List<ServiceType> serviceTypeList = orderService.findAllSericeType();
       return ResponseBean.success(serviceTypeList);

    }

    @GetMapping("/parts/types")
    @ResponseBody
    public ResponseBean partsTypes(){
        List<Type> partsList = orderService.findAllPartsType();
        return ResponseBean.success(partsList);

    }

    @GetMapping("/{id:\\d+}/parts")
    @ResponseBody
    public ResponseBean partsByType(@PathVariable Integer id){
        List<Parts> partsList = partsService.findPartsByType(id);
        return ResponseBean.success(partsList);
    }

}
