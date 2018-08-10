package com.kaisheng.it.mq;

import com.kaisheng.it.service.FixOrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.listener.SessionAwareMessageListener;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;

/**
 * @author guojiabang
 * @date 2018/8/9 0009
 */
public class FixOrderListener implements SessionAwareMessageListener {

    private Logger logger = LoggerFactory.getLogger(FixOrderListener.class);

    @Autowired
    public FixOrderService fixOrderService;

    @Override
    public void onMessage(Message message, Session session) throws JMSException {

        //创建TextMessage
        TextMessage textMessage = (TextMessage) message;

        try {
            // 获得列队中的json数据
            String json = textMessage.getText();
            logger.info("接受列队中的json信息: {}",json);
            fixOrderService.createFixOrder(json);

            textMessage.acknowledge();
        } catch (JMSException e) {
            e.printStackTrace();
            session.recover();
        }

    }


}
