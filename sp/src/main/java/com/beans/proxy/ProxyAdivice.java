package com.beans.proxy;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

public class ProxyAdivice implements MethodInterceptor{

    public Object invoke(MethodInvocation invocation) throws Throwable {
        System.out.println("开始调用");
        return null;
    }

    
}
