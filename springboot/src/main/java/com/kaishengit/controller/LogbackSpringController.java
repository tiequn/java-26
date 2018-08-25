package com.kaishengit.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author guojiabang
 * @date 2018/8/23 0023
 */
@RestController
public class LogbackSpringController {

    Logger logger = LoggerFactory.getLogger(LogbackSpringController.class);

    @GetMapping("/logback")
    public String logbackSpring(String message){

        logger.debug("添加日志:{}",message);

        return "mwssage" + message;
    }

}
