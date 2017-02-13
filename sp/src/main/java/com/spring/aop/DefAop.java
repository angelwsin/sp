package com.spring.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;




@Aspect
public class DefAop {

    @Pointcut("execution(* com.spring.aop.*.*(..))")
    public void  conf(){}
    
    
    @Before("conf()")
    public void  before(){
        System.out.println("做饭");
    }
}
