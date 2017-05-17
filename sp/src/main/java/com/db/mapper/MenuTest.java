package com.db.mapper;

import java.io.InputStream;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.db.bean.Menu;

public class MenuTest {
    
    public static void main(String[] args) {
        InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("conf/myBatis_conf.xml");
        SqlSessionFactory  sessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession =  sessionFactory.openSession();
        Configuration conf = sessionFactory.getConfiguration();
       
        MenuMapper menuMapper = sessionFactory.getConfiguration().getMapper(MenuMapper.class, sqlSession);
        Menu menu = new Menu();
        menu.setName("menu");
        menuMapper.insertMenu(menu);
        
      
            AtomicInteger    index = new AtomicInteger(0);
            index.incrementAndGet();
            System.out.println(index.get());
        

    }

}
