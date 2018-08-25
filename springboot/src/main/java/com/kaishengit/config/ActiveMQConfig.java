package com.kaishengit.config;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.RedeliveryPolicy;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.jms.DefaultJmsListenerContainerFactoryConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.jms.support.converter.MessageType;

import javax.jms.ConnectionFactory;
import javax.jms.MessageConsumer;
import javax.validation.Valid;

/**
 * @author guojiabang
 * @date 2018/8/25 0025
 */
@Configuration
@EnableJms
public class ActiveMQConfig {

    @Value("${spring.activemq.broker-url}")
    private String brokerUrl;

    /**
     * Topic监听器
     * 配置JmsListenerContainerFactory，设置为pub/sub模式
     * @param connectionFactory
     * @param containerFactoryConfigurer
     * @return
     */
    @Bean
    public JmsListenerContainerFactory jmsTopicListenerContainerFactory(ConnectionFactory connectionFactory,
                                                                        DefaultJmsListenerContainerFactoryConfigurer containerFactoryConfigurer){
        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
        containerFactoryConfigurer.configure(factory, connectionFactory);
        //设置发布订阅模式
        factory.setPubSubDomain(true);
        return factory;
    }

    /**
     * 重试机制
     * @return
     */
    @Bean
    public ActiveMQConnectionFactory activeMQConnectionFactory(){

        RedeliveryPolicy redeliveryPolicy = new RedeliveryPolicy();
        //重试次数
        redeliveryPolicy.setMaximumRedeliveries(3);
        //初次延迟时间
        redeliveryPolicy.setMaximumRedeliveryDelay(3000);
        //每次延迟时间
        redeliveryPolicy.setRedeliveryDelay(3000);

        ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory();
        activeMQConnectionFactory.setRedeliveryPolicy(redeliveryPolicy);
        activeMQConnectionFactory.setBrokerURL(brokerUrl);

        return activeMQConnectionFactory;

    }

    /**
     * 发送Java对象到消息队列中
     * 定义消息转换器
     * @return
     */
    @Bean
    public MessageConverter jacksonMessageConverter(){

        MappingJackson2MessageConverter messageConverter = new MappingJackson2MessageConverter();
        messageConverter.setTargetType(MessageType.TEXT);
        messageConverter.setTypeIdPropertyName("_type");

        return messageConverter;
    }


}
