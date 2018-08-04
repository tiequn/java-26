package com.kaisheng.it.controller;

import com.github.pagehelper.PageInfo;
import com.kaisheng.it.entity.Parts;
import com.kaisheng.it.entity.Type;
import com.kaisheng.it.service.PartsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author guojiabang
 * @date 2018/7/23 0023
 */
@Controller
@RequestMapping("/parts")
public class PartsController {

    @Autowired
    private PartsService partsService;

    @GetMapping
    public String list(@RequestParam(name = "p", defaultValue = "1") Integer pageNo,
                       @RequestParam(required = false) String partsName,
                       @RequestParam(required = false) Integer partsType,
                       @RequestParam(required = false) Integer inventory,
                       Model model){
        // 封装筛选的queryMap集合
        Map<String,Object> queryMap = new HashMap<>();
        queryMap.put("partsName", partsName);
        queryMap.put("partsType", partsType);
        queryMap.put("inventory", inventory);

        PageInfo<Parts> pageInfo = partsService.findPageByPageNoAndQueryMap(pageNo,queryMap);

        // 封装typeList
        List<Type> typeList = partsService.findTypeList();
        model.addAttribute("typeList",typeList);
        model.addAttribute("pageInfo",pageInfo);

        return "parts/list";
    }

    @GetMapping("/{id:\\d+}")
    public String findById(@PathVariable Integer id, Model model) throws IOException{

        Parts parts = partsService.findById(id);

        if(parts == null){
            // 抛出异常
            throw new IOException();
        } else {
            model.addAttribute("parts",parts);
        }
        return "parts/detail";
    }

    @GetMapping("/del/{id:\\d+}")
    public String delById(@PathVariable Integer id, RedirectAttributes redirectAttributes){

        partsService.delById(id);
        redirectAttributes.addFlashAttribute("message","商品已删除");

        return "redirect:/parts";
    }

    @GetMapping("/{id:\\d+}/edit")
    public String partsEdit(@PathVariable Integer id, Model model){

        Parts parts = partsService.findById(id);

        //封装typeList
        List<Type> typeList = partsService.findTypeList();
        model.addAttribute("typeList",typeList);
        model.addAttribute("parts",parts);

        return "parts/edit";
    }

    @PostMapping("/{id:\\d+}/edit")
    public String partsEdit(Parts parts, RedirectAttributes redirectAttributes){
        partsService.partsEdit(parts);
        redirectAttributes.addFlashAttribute("message", "更新成功");

        return "redirect:/parts";
    }

    @GetMapping("/add")
    public String partsAdd(Model model){
        // 封装typeList
        List<Type> typeList = partsService.findTypeList();
        model.addAttribute(typeList);

        return "parts/add";
    }

    @PostMapping("/add")
    public String partsAdd(Parts parts, RedirectAttributes redirectAttributes){
        partsService.saveParts(parts);
        redirectAttributes.addFlashAttribute("message","入库成功");

        return "redirect:/parts";
    }

    /*@GetMapping("/check/partsNo")
    @ResponseBody
    public JSONPObject check(HttpServletRequest req,HttpServletResponse resp){

        String partsNo = req.getParameter("partsNo");

        List<Parts> partsList = partsService.findTypeList(partsNo);

        return null;
    }*/



}