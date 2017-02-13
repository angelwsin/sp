package com.MBean;

import javax.management.MXBean;

@MXBean
public interface EchoMBean {
    
    
    public void print(String name);

}
