package com.Introspector;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;

import com.def.tag.User;

public class IntrospectorTest {
    
    
    public static void main(String[] args) throws Exception{
        User user = new User();
        BeanInfo beanInfo = Introspector.getBeanInfo(User.class,Introspector.USE_ALL_BEANINFO);
        PropertyDescriptor[] propsDesc = beanInfo.getPropertyDescriptors();
        for(PropertyDescriptor desc : propsDesc){
            Method method =  desc.getWriteMethod();
           if(method!=null&&"userName".equals(desc.getName())){
               method.invoke(user, "zhansan");
           }
            
        }
        System.out.println(user.toString());
    }

}
