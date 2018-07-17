package com.kaishengit.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author guojiabang
 * @date 2018/7/16 0016
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring.xml")
public class UserServiceTestCase {

    @Autowired
    private UserService userService;

    @Test
    public void testServicer(){

        // ApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
        // UserService userService = (UserService) context.getBean("userService");
        userService.save();

    }


}
