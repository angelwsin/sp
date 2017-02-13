package com.ce;

import java.lang.reflect.Field;
import java.util.Vector;

public class ClassLoaderTest {
    
    
    public static void main(String[] args) throws Exception{
        System.out.println(Thread.currentThread().getContextClassLoader());
        System.out.println(ClassLoaderTest.class.getClassLoader());
        Field  field =   ClassLoader.class.getDeclaredField("classes");
          field.setAccessible(true);
          Vector  load = (Vector) field.get(ClassLoader.getSystemClassLoader());
          
          for(int i=0;i<load.size();i++){
              System.out.println(load.get(i));
          }
         // Thread.sleep(50000);
          new MXBeanTest();
          
          Vector  load2 = (Vector) field.get(ClassLoader.getSystemClassLoader());
          
          for(int i=0;i<load2.size();i++){
              System.out.println(load2.get(i));
          }
          
          
          Thread.sleep(Integer.MAX_VALUE);
        
    }

}
