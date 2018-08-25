package com.kaishengit.controller;

import com.kaishengit.entity.TUser;
import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author guojiabang
 * @date 2018/8/25 0025
 */
@RestController
public class ActiveMQContrtoller {

    @Autowired
    private JmsTemplate jmsTemplate;

    @GetMapping("/queue")
    public String sendQueue(){

        ActiveMQQueue activeMQQueue = new ActiveMQQueue("springBoot-queue");

        jmsTemplate.send(activeMQQueue, session -> session.createTextMessage("Hello,SpringBootQueue"));
        return "success";
    }

    @GetMapping("/topic")
    public String sendTopic(){
        ActiveMQTopic activeMQTopic = new ActiveMQTopic("springBoot-topic");
        jmsTemplate.send(activeMQTopic, session -> session.createTextMessage(" Hello, SpringBootTopic"));
        return "success";
    }

    @GetMapping("/toqueue")
    public void sendUserToQueue(){

        TUser tUser = new TUser("tom","jz");
        jmsTemplate.convertAndSend("springBoot-user",tUser);
    }

}
