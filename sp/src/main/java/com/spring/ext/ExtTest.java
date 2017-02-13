package com.spring.ext;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ExtTest {

    
    public static void main(String[] args) {
        
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:ext.xml");
        
        CompontService compont = (CompontService)context.getBean("compontService");
        compont.sayHello("x");
    }
}
