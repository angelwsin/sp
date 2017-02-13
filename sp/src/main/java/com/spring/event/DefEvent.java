package com.spring.event;

import org.springframework.context.ApplicationEvent;

public class DefEvent  extends ApplicationEvent{

    /**  */
    private static final long serialVersionUID = 1L;

    public DefEvent(Object source) {
        super(source);
    }
    
    
    public void  say(){
        System.out.println("say ");
    }

}
