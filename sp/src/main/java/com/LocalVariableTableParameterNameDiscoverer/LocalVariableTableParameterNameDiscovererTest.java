package com.LocalVariableTableParameterNameDiscoverer;

import java.lang.reflect.Method;

import org.springframework.core.LocalVariableTableParameterNameDiscoverer;

import com.def.tag.UserBeanDefineParse;

public class LocalVariableTableParameterNameDiscovererTest {
    
    /**
     * 解析方法的参数名
     * 
     * @param args
     */
    public static void main(String[] args) {
        LocalVariableTableParameterNameDiscoverer disc = new LocalVariableTableParameterNameDiscoverer(); 
        Method[] methods  = UserBeanDefineParse.class.getDeclaredMethods();
        for(Method method : methods){
           System.out.println(method.getName()+"-"+disc.getParameterNames(method)[0]+" params:"+disc.getParameterNames(method)[0]);
        }
       
    }

}
