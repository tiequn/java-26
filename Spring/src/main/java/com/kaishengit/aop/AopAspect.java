package com.kaishengit.aop;

import org.aspectj.lang.ProceedingJoinPoint;

/**
 *  Aop 通知类
 *
 * @author guojiabang
 * @date 2018/7/16
 */
public class AopAspect {

    public void beforeAdvice() {
        System.out.println("前置通知");
    }

    public void afterAdvice(Object result) {
        System.out.println("后置通知-->result--->" + result);
    }

    public void exceptionAdvice(Exception ex) {
        System.out.println("异常通知-->ex-->" + ex.getMessage());
    }

    public void finallyAdvice() {
        System.out.println("最终通知");
    }

    // 环绕通知
    public Object aroundAdvice(ProceedingJoinPoint proceedingJoinPoint){

        System.out.println("-------》前");

        Object result = null;

        try {
            result = proceedingJoinPoint.proceed();
            System.out.println("-----》后");
        } catch (Throwable throwable) {
            System.out.println("-----》异常");
        } finally {
            System.out.println("-------》最");
        }

        return result;

    }

}

