package com.ce;

import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;

import javax.management.MBeanServer;


/**
 * http://blog.csdn.net/gaojava/article/details/8767597  可参考
 * JVM本身提供了一组管理的API，通过该API，我们可以获取得到JVM内部主要运行信息，包括内存各代的数据、JVM当前所有线程及其栈相关信息等等。
 * 各种JDK自带的剖析工具，包括jps、jstack、jinfo、jstat、jmap、jconsole等，都是基于此API开发的
 * 
 *  JVM Management API
 *  JMX-MBean
 * @version $Id: MXBeanTest.java, 
 */
public class MXBeanTest {
    
    
    static{
        System.out.println(Thread.currentThread().getContextClassLoader());
        System.out.println(ClassLoaderTest.class.getClassLoader());
          System.out.println("static ");
    }
    
    public static void main(String[] args) throws Exception{
        //我们可以通过JMX的方式读取到JVM Manage定义的MBean
        MBeanServer server = ManagementFactory.getPlatformMBeanServer();  
        RuntimeMXBean rmxb = ManagementFactory.newPlatformMXBeanProxy(server,  
                        "java.lang:type=Runtime", RuntimeMXBean.class);  
        
    }

}
