package com.spring.handler;

import org.springframework.beans.factory.xml.NamespaceHandlerSupport;

public class DefAopNamespaceHandler extends NamespaceHandlerSupport{

    public void init() {
       // registerBeanDefinitionParser("aspectj-autoproxy", new AspectJAutoProxyBeanDefinitionParser());
        
      //  AnnotationAwareAspectJAutoProxyCreator
    }

}
