package com.MBean;

import java.lang.management.ManagementFactory;

import javax.management.MBeanServer;
import javax.management.ObjectName;

/*
 *  MBean之间的通信是必不可少的，Notification就起到了在MBean之间沟通桥梁的作用。JMX 的通知由四部分组成：

1、Notification这个相当于一个信息包，封装了需要传递的信息

2、Notification broadcaster这个相当于一个广播器，把消息广播出。

3、Notification listener 这是一个监听器，用于监听广播出来的通知信息。

4、Notification filiter 这个一个过滤器，过滤掉不需要的通知。这个一般很少使用。
 */
public class NotificationMBeanTest {
    
    
    public static void main(String[] args)throws Exception {
        MBeanServer mServer =   ManagementFactory.getPlatformMBeanServer();
        ObjectName helloName = new ObjectName("wq:name=hello");
        Hello hello=new Hello();   
        mServer.registerMBean(hello, helloName);
        ObjectName jackName = new ObjectName("jack:name=jack");
        Jack  jack = new Jack();
        mServer.registerMBean(jack, jackName);
        jack.addNotificationListener(new HelloListener(), null, hello);
        
        Thread.sleep(500000);
    }

}
