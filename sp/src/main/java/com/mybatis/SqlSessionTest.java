package com.mybatis;

import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.mybatis.mapper.UserMapper;
import com.vo.User;


public class SqlSessionTest {
    
    
    public static void main(String[] args) {
       InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("conf/myBatis_conf.xml");
        SqlSessionFactory  sessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession =  sessionFactory.openSession();
        Configuration conf = sessionFactory.getConfiguration();
       
        UserMapper userMapper = sessionFactory.getConfiguration().getMapper(UserMapper.class, sqlSession);
        User user = new User();
        user.setName("myBatis");
        user.setAge(6);
        userMapper.insertUser(user);
        sqlSession.commit();
        Paging<User> pagin = new Paging<User>();
        User param = new User();
        param.setName("myBatis");
        pagin.setParam(param);
        List<User> uList =  userMapper.selectUserPaging(pagin);
        System.out.println(pagin.getPageNumber());
        
    }

}
