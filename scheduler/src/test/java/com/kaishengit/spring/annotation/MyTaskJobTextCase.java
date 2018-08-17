package com.kaishengit.spring.annotation;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.xml.ws.ResponseWrapper;
import java.io.IOException;

/**
 * @author guojiabang
 * @date 2018/8/16 0016
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring-task-annotation.xml")
public class MyTaskJobTextCase {

    @Test
    public void textRun() throws IOException {

        System.out.println("容器启动了");
        System.in.read();

    }
}
