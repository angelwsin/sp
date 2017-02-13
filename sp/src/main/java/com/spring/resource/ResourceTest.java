package com.spring.resource;

import java.io.IOException;

import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;



/*
 * 对于java中资源封装的不足 spring封装一套自己的资源
 * 
 * 以InputStreamSource, Resource作为资源的顶层接口
 * 实现方式还是java的
 * 如 ClassPathResource  实现 getClassLoader().getResource(name)
 * 
 * ResourceLoader  用于加载 Resource
 * ResourcePatternResolver
 * PathMatchingResourcePatternResolver 
 */
public class ResourceTest {

    public static void main(String[] args) throws Exception{
        
        PathMatchingResourcePatternResolver resolver  = new PathMatchingResourcePatternResolver(new DefaultResourceLoader());
        Resource[] resources =  resolver.getResources("classpath:biz.xml");
        for(Resource res :resources){
            System.out.println(res.getFilename());
        }
        
        
    }
    
    public Resource[] getConfigResource(String localPath)throws IOException{
        PathMatchingResourcePatternResolver resolver  = new PathMatchingResourcePatternResolver(new DefaultResourceLoader());
        Resource[] resources = resolver.getResources(localPath);
        return resources;
    }
}
