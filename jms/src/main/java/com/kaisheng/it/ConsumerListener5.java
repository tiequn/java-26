package com.kaisheng.it;


import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.jms.Message;
import javax.jms.Session;

/**
 * @author guojiabang
 * @date 2018/8/7 0007
 */
@Component
public class ConsumerListener5 {

    @JmsListener(destination = "spring-queue",containerFactory = "jmsListenerContainerFactory")
    public void getMessage(Message message, Session session) {
        System.out.println("接受消息：" + message);
    }


}
