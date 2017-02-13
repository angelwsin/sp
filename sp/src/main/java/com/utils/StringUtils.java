package com.utils;

public class StringUtils {
    
    
    
    public static boolean  isEmpty(String str){
        return str==null||str.length()==0;
    }
    
    public static String getValue(String value,String def){
         return isEmpty(value)?def:value;
    }

}
