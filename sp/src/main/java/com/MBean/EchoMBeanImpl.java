package com.MBean;

public class EchoMBeanImpl implements EchoMBean{

    public void print(String name) {
        System.out.println("hi "+name);
    }

}
