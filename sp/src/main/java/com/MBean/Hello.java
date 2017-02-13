package com.MBean;

public class Hello implements HelloMBean{
    
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
            this.name = name;
    }

    public void printHello() {
        System.out.println(name+" "+"hello");
    }

    public void printHello(String whoName) {
        System.out.println(whoName+" "+"hello");
    }

}
