package com.kaisheng.it;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

/**
 * @author guojiabang
 * @date 2018/8/13 0013
 */
public class App {

    public static void main(String[] args) throws IOException {

        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring.xml");
        context.start();
        System.out.println("容器启动成功");

        System.in.read();

    }

}
