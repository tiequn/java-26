package com.kaishengit.controller;

import com.github.pagehelper.PageInfo;
import com.kaishengit.entity.Parts;
import com.kaishengit.service.PartsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.util.List;

/**
 * @author guojiabang
 * @date 2018/7/23 0023
 */
@Controller
@RequestMapping("/parts")
public class PartsController {

    @Autowired
    private PartsService partsService;

    @GetMapping("/list")
   public String list(@RequestParam(name = "p", defaultValue = "1") Integer pageNo, Model model){

        PageInfo<Parts> pageInfo = partsService.findPage(pageNo);
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

        return "/parts/detail";

    }

}
