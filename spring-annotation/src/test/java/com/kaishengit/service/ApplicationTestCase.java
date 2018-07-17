package com.kaishengit.service;

import com.kaishengit.Application;
import com.kaishengit.dao.UserDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author guojiabang
 * @date 2018/7/17 0017
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = Application.class)
public class ApplicationTestCase {

    @Autowired
    private UserDao userDao;

    @Test
    public void testApplication(){

       // ApplicationContext context = new AnnotationConfigApplicationContext(Application.class);
        // UserDao userDao = (UserDao) context.getBean("userDao");

        userDao.save();
    }


}
