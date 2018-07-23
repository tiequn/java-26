package com.kaishengit.controller;

import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.*;

/**
 * @author guojiabang
 * @date 2018/7/23 0023
 */
@Controller
@RequestMapping("/file")
public class FileUpload {

    @GetMapping("/upload")
    public String fileUpload(){
        return "file/fileUpload";
    }

    @PostMapping("/upload")
    public String upload(MultipartFile file, RedirectAttributes attributes){

        if(!file.isEmpty()){
            try {
                // 上传控件的 name 的属性
                System.out.println(file.getName());
                // 上传文件的大小
                System.out.println(file.getSize());
                // 上传文件是否为空
                System.out.println(file.isEmpty());
                // 上传文件原始名
                System.out.println(file.getOriginalFilename());
                // MIME类型
                System.out.println(file.getContentType());

                // 文件上传
                File direction = new File("G:temp/img");
                // 判断文件夹是否存在  如不存在则创建文件夹 mkdirs创建文件夹及其子文件夹，mkdir创建当前文件夹
                if(!direction.exists()){
                    direction.mkdirs();
                }

                OutputStream outputStream = new FileOutputStream(new File(direction,file.getOriginalFilename()));
                InputStream inputStream = file.getInputStream();

                IOUtils.copy(inputStream,outputStream);

                outputStream.flush();
                outputStream.close();
                inputStream.close();

            }catch (IOException e) {
                e.printStackTrace();
            }

        } else {

            attributes.addFlashAttribute("message","文件不能为空");

        }


        return "redirect:/file/upload";

    }

}
