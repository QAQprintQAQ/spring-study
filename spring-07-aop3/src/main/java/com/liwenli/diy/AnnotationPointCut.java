package com.liwenli.diy;

/*
* 方式三:通过使用注解方式实现aop
* */

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

@Aspect//标注这个类是一个切面
public class AnnotationPointCut {

    @Before("execution(* com.liwenli.service.UserServiceImpl.*(..))")//切入点的位置就是在UserServiceImpl中
    public void before() {
        System.out.println("==========方法执行前===========");

    }


    @After("execution(* com.liwenli.service.UserServiceImpl.*(..))")
    public void after() {
        System.out.println("=========方法执行后============");

    }

    //    在环绕增强中，我们可以给定一个参数,代表我们要获取处理切入的点
    @Around("execution(* com.liwenli.service.UserServiceImpl.*(..))")
    public void around(ProceedingJoinPoint joinPoint) throws Throwable {

        System.out.println("环绕前");

        joinPoint.getSignature();//获得签名
        Object proceed = joinPoint.proceed();//执行方法
        System.out.println("环绕后");

        System.out.println(proceed);

    }

}
