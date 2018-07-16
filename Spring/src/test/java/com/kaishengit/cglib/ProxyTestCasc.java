package com.kaishengit.cglib;

import com.kaishengit.proxy.cglib.ProxySale;
import com.kaishengit.proxy.cglib.Sale;
import net.sf.cglib.proxy.Callback;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
import org.junit.Test;

import java.lang.reflect.Method;

/**
 * @author guojiabang
 * @date 2018/7/16 0016
 */
public class ProxyTestCasc {

    @Test
    public void testProSale(){
        ProxySale proxySale = new ProxySale();
        proxySale.salePc();
    }

    @Test
    public void testCglib(){

        // 创建Enhancer对象
        Enhancer enhancer = new Enhancer();

        //  设置属性
        //  通过setSuperclass设置对象
        enhancer.setSuperclass(Sale.class);

        enhancer.setCallback(new MethodInterceptor() {

            /**
             *
             * @param target 目标对象
             * @param method 暂时没用
             * @param args 方法参数列表
             * @param methodProxy  产生父类方法的代理方法对象
             * @return
             * @throws Throwable
             */
            @Override
            public Object intercept(Object target, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {

                System.out.println("——————————————>");
                // 执行父类（目标对象）的方法
                Object result = methodProxy.invokeSuper(target, args);
                System.out.println("==============>");

                return result;
            }

        });

        // 动态产生代理对象
        Sale sale = (Sale)enhancer.create();

        System.out.println(sale.getClass().getName());

        sale.salePc();
    }

}
