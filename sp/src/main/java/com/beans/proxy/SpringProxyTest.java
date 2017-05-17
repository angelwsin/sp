package com.beans.proxy;

import org.springframework.aop.framework.ProxyFactory;

public class SpringProxyTest {
    
    
    
    public static void main(String[] args) {
        
        ProxyFactory factory = new ProxyFactory();
        factory.setInterfaces(UserService.class);
        factory.addAdvice(new ProxyAdivice());
        UserService userService =  (UserService) factory.getProxy();
        userService.say();
        
    }

}
