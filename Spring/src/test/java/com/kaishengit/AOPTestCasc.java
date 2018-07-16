package com.kaishengit;

import com.kaishengit.proxy.jdk.Sale;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author guojiabang
 * @date 2018/7/16 0016
 */
public class AOPTestCasc {

    @Test
    public void testAop(){

        // 获取Spring容器
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-aop.xml");

        // 从容器spring中获得bean
        // 此时获得的lenovo是增强过后的动态代理类对象，因此不能强转为Lenovo,否则会抛出java.lang.ClassCastException:
        Sale sale = (Sale) context.getBean("lenovo");

       // System.out.println(sale.getClass().getName());

        sale.salePc();

        int price = sale.salePrice();

    }


}
