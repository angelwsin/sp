package com.classloader;

import java.lang.reflect.Field;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;

import sun.misc.URLClassPath;

public class LoaderTest   extends URLClassLoader{

    public LoaderTest(URL[] urls, ClassLoader parent) {
        super(urls, parent);
    }
    
    //protocol://host:port/path?query#ref
    //资源与jar文件中的资源的URL的协议是不同的 开头分别为file:/和jar:file:/
    //jar文件的URL jar:http://hostname/my.jar!/
    //指向文件系统的URL jar:file:/c:/almanac/my.jar!/
    //指向jar文件里一个入口的URL jar:file:/c:/almanac/my.jar!/com/mycompany/MyClass.class
    @SuppressWarnings("restriction")
    public static void main(String[] args) throws Exception{
        URL  rul  =new URL("jar:file:/D://alipayWorkspace//ce//lib//com.alipay.cloudengine.datasource.driver-3.4.0.1.jar!/");
       
      //  LoaderTest ts = new LoaderTest(new URL[]{rul}, ClassLoader.getSystemClassLoader());
        URLClassLoader ts = (URLClassLoader)Thread.currentThread().getContextClassLoader();

      /*  Class<?> clzz =  ts.loadClass("com.alipay.zdal.datasource.oci.ZDataSourceOciDriver");
        System.out.println(clzz);*/
        URL[] urls =   ts.getURLs();
        for(URL u:urls){
            System.out.println(u.getPath());
        }
      Field field =   URLClassLoader.class.getDeclaredField("ucp");
      field.setAccessible(true);
      @SuppressWarnings("unused")
    URLClassPath path = (URLClassPath)field.get(ts);
      Field ucp_loadersField = URLClassPath.class.getDeclaredField("loaders");
      ucp_loadersField.setAccessible(true);
      ArrayList loaders = (ArrayList) ucp_loadersField.get(path);
      Class jarLoaderClass = null;
      Field csu = null;
      // 遍历所有的 Loader，Loader 可能是 JarLoader 或者 FileLoader，我们想要的是 JarLoader，但是由于 JarLoader 的 Scope 是 Package 的
      // 无法直接通过 ClassLoader 来加载，所以必须遍历所有的 Loader，如果拿到的 Loader 有名称为 csu 的 Field，就确定当前的 Loader 是 JarLoader
      for(Object loader : loaders){
          jarLoaderClass = loader.getClass();
          try {
              csu = jarLoaderClass.getDeclaredField("csu");
              csu.setAccessible(true);
              break;
          } catch (NoSuchFieldException e) {
              // Ignore
              e.printStackTrace();
          }
      }
       
    }

}
