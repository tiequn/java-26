package com.kaishengit.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

/**
 * @author guojiabang
 * @date 2018/7/16 0016
 */
@Component
@Aspect
public class AopAspect {

     // 定义切入点：在什么类的什么方法上添加功能
    @Pointcut("execution(* com.kaishengit.service..*.*(..))")
    public void pointCut(){}

    @Before("pointCut()")
    public void beforeAdvice(JoinPoint joinPoint){
        String methodName = joinPoint.getSignature().getName();
        System.out.println(methodName + "马上执行了...");
    }

    @AfterReturning(value = "pointCut()", returning = "result")
    public void afterAdvice(JoinPoint joinPoint, Object result){
        String methodName = joinPoint.getSignature().getName();
        System.out.println(methodName + "已经执行了..." + result);
    }

    @AfterThrowing(value = "pointCut()", throwing = "ex")
    public void afterThrowing(JoinPoint joinPoint, Exception ex){
        String methodName = joinPoint.getSignature().getName();
        System.out.println(methodName + "抛异常... ex :" + ex);
    }

    @After("pointCut()")
    public void finallyAdvice(JoinPoint joinPoint){
        String methodName = joinPoint.getSignature().getName();
        System.out.println(methodName + "\"最终都要执行的..");
    }

    @Around("pointCut()")
    public Object aroundAdvice(ProceedingJoinPoint proceedingJoinPoint){

        Object result = null;

        System.out.println("---------->");
        try {
            result = proceedingJoinPoint.proceed();
            System.out.println("+++++++++>");
        } catch (Throwable throwable) {
            System.out.println("))))))))))");
        } finally {
            System.out.println("!!!!!!!!!!");
        }
        return result;

    }

}
