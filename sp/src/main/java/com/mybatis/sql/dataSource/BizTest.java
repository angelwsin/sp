package com.mybatis.sql.dataSource;

import java.sql.Connection;

import javax.sql.DataSource;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.mybatis.mapper.UserMapper;
import com.vo.User;



public class BizTest {
    
    public static void main(String[] args) throws Exception{
         ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("biz.xml");
         DataSource dataSource = (DataSource) context.getBean("dataSource");
         Connection con =   dataSource.getConnection();
         System.out.println(con.getMetaData().getDatabaseProductName());
         UserMapper userMapper = context.getBean(UserMapper.class);
         User user = new User();
         user.setAge(45);
         userMapper.insertUser(user);
    }

}
