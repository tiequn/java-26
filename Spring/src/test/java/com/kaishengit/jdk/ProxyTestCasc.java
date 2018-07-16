package com.kaishengit.jdk;

import com.kaishengit.proxy.jdk.Lenovo;
import com.kaishengit.proxy.jdk.Sale;
import com.kaishengit.proxy.jdk.MyInvocationHandler;
import org.junit.Test;

/**
 * @author guojiabang
 * @date 2018/7/16 0016
 */
public class ProxyTestCasc {

    @Test
    public void testDynamicProxy(){

    Lenovo lenovo = new Lenovo();

    // 创建 MyInvocationHandler 对象
    MyInvocationHandler invocationHandler = new MyInvocationHandler(lenovo);

    // 动态产生代理对象  (使用jdk中的Proxy)
     Sale sale = (Sale) java.lang.reflect.Proxy.newProxyInstance(Lenovo.class.getClassLoader(),
             Lenovo.class.getInterfaces(),
             invocationHandler);

     // 获得完全限定名
        System.out.println(sale.getClass().getName());
        sale.salePc();

    }

}
