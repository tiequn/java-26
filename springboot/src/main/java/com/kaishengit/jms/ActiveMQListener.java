package com.kaishengit.jms;

import com.kaishengit.entity.TUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

/**
 * @author guojiabang
 * @date 2018/8/25 0025
 */
@Component
public class ActiveMQListener {

    private Logger logger = LoggerFactory.getLogger(ActiveMQListener.class);

    @JmsListener(destination = "springBoot-queue")
    public void springBootQueueListener(String message){
        logger.debug("接收到监听信息:{}", message);
   }

   @JmsListener(destination = "springBoot-topic", containerFactory = "jmsTopicListenerContainerFactory")
   public void springBootTopic(String message){
       logger.debug("接收到监听信息:{}", message);
   }

    @JmsListener(destination = "springBoot-user")
    public void getUserFromQueue(TUser tUser) {
        System.out.println("User Object : " + tUser);
    }

}
