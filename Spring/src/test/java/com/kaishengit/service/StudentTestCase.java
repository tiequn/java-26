package com.kaishengit.service;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author guojiabang
 * @date 2018/7/14 0014
 */
public class StudentTestCase {

    @Test
    public void testSet(){
        // 获取spring容器
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        // 通过容器spring获取Bean
        StudentService studentService = (StudentService) context.getBean("studentService");

        studentService.save();

    }

}
