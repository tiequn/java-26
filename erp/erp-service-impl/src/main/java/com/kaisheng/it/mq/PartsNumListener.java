package com.kaisheng.it.mq;

import com.kaisheng.it.service.PartsService;
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
 * @date 2018/8/10 0010
 */
public class PartsNumListener implements SessionAwareMessageListener {

    private Logger logger = LoggerFactory.getLogger(PartsNumListener.class);

    @Autowired
    private PartsService partsService;

    @Override
    public void onMessage(Message message, Session session) throws JMSException {
        //创建TextMessage
        TextMessage textMessage = (TextMessage) message;

        try {
            // 获得列队中的json数据
            String json = textMessage.getText();
            logger.info("接受的队列json数据:{}", json);
            partsService.subInventory(json);

            textMessage.acknowledge();
        } catch (JMSException e) {
            e.printStackTrace();
            session.recover();
        }

    }
}
