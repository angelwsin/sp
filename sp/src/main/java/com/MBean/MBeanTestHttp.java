package com.MBean;

import java.lang.management.ManagementFactory;

import javax.management.MBeanServer;
import javax.management.ObjectInstance;
import javax.management.ObjectName;

import com.sun.jdmk.comm.HtmlAdaptorServer;


// 可以使用 web 页面 进行管理   需要引入 jdmk jar
public class MBeanTestHttp {
    
    
    public static void main(String[] args) throws Exception{
        //创建MBeanServer 
        
        MBeanServer server =   ManagementFactory.getPlatformMBeanServer();
     // 新建MBean ObjectName, 在MBeanServer里标识注册的MBean 包名:type=类名
     // 或“域名：name=MBean名称”，其中域名和MBean的名称可以任意取
        ObjectName name  = new ObjectName("com.MBean:type=EchoMBeanImpl");
     // 在MBeanServer里注册MBean, 标识为ObjectName
        ObjectInstance instance =  server.registerMBean(new EchoMBeanImpl(), name);
        //server.invoke(name, "print", new Object[]{"zhangsan"}, new String[] {"java.lang.String"});
        ObjectName adapterName = new ObjectName("MBeanTestHttp:name=htmladapter,port=8082");
        HtmlAdaptorServer adapter = new HtmlAdaptorServer();   
        server.registerMBean(adapter, adapterName);  
        adapter.start();
       // Thread.sleep(Long.MAX_VALUE);
    }

}
