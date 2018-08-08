package com.kaisheng.it;


import org.springframework.jms.listener.SessionAwareMessageListener;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;

/**
 * @author guojiabang
 * @date 2018/8/7 0007
 */
@Component
public class ConsumerListener4 implements SessionAwareMessageListener {

    public void onMessage(Message message, Session session) throws JMSException {
        TextMessage textMessage = (TextMessage) message;

        try {
            System.out.println("====>" + textMessage.getText());
            if(textMessage.getText().startsWith("spring,mq")) {
                throw new JMSException("出异常了");
            }

            textMessage.acknowledge();
        } catch (JMSException e) {
            e.printStackTrace();
            session.recover();
        }
    }
}
