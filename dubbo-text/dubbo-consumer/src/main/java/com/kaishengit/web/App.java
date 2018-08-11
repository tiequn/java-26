package com.kaishengit.web;

import com.kaishengit.service.ProviderService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

/**
 * @author guojiabang
 * @date 2018/8/11 0011
 */
public class App {

    public static void main(String[] args) throws IOException {

        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring-dubbo-consumer.xml");
        ProviderService providerService = (ProviderService) context.getBean("rpcProviderService");

        String name = providerService.findBId(1001);
        System.out.println("name---->" + name);
        System.in.read();

    }

}
