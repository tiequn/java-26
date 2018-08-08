package com.kaisheng.it;


import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

/**
 * @author guojiabang
 * @date 2018/8/7 0007
 */
@Component
public class ConsumerListener3 {

    @JmsListener(destination = "spring-queue")
    public void getMessage(String message){
        System.out.println("接受信息:" + message);
    }


}
