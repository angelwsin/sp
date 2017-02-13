package com.unsafe;



public class DefUnsofe {
    
    public static void main(String[] args)throws Exception {
           Class<?> clazz =   Class.forName("sun.misc.Unsafe");
           clazz.getMethod("getUnsafe", null).invoke(null, null);
       
}
    
   
    public static void say(){
        System.out.println("day");
    }
}