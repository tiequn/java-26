package com.kaishengit.service;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.*;

/**
 * @author guojiabang
 * @date 2018/7/14 0014
 */
public class BaseTestCase {

    @Test
    public void show(){

    // 获取spring容器
    ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
    // 通过容器spring获取Bean
    BaseService baseService = (BaseService)context.getBean("baseService");

        System.out.println(baseService.getId());
        System.out.println(baseService.getName());
        System.out.println(baseService.getScore());

        List<String> nameList =baseService.getStrList();
        for (String name : nameList){
            System.out.println(name);
        }

        Set<Integer> numSets = baseService.getNumSets();
        for (Integer num : numSets){
            System.out.println(num);
        }

        Map<String,String> maps = baseService.getMaps();
        for (Map.Entry<String,String> entry : maps.entrySet()) {
            System.out.println(entry.getKey());
            System.out.println(entry.getValue());
        }

        Properties properties = baseService.getProperties();
        Enumeration<Object> keys = properties.keys();

        // 通过 hasMoreElements() 方法判断keys是否有元素
        while(keys.hasMoreElements()){
            Object key = keys.nextElement(); // 通过nextElement()输出下一个
            Object value = properties.get(key);
            System.out.println(key);
            System.out.println(value);
        }



    }


}
