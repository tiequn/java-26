package com.kaishengit.test;

import com.kaishengit.dao.UserDao;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author guojiabang
 * @date 2018/7/13 0013
 */
public class SpringTestCase {

    @Test
    public void testSpring(){

        // 获取Spring容器
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        // 从Spring容器中获取Bean
        UserDao userDao = (UserDao) context.getBean("userDao");
        userDao.save();

    }



}
