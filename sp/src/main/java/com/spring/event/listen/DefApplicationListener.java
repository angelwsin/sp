package com.spring.event.listen;

import org.springframework.context.ApplicationListener;

import com.spring.event.DefEvent;

public class DefApplicationListener  implements ApplicationListener<DefEvent>{

    public void onApplicationEvent(DefEvent event) {
        event.say();
        
    }

   
}
