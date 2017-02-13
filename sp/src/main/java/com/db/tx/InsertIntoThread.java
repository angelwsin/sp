package com.db.tx;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.TransactionIsolationLevel;

import com.mybatis.mapper.UserMapper;
import com.vo.User;

public class InsertIntoThread extends DBThread{
      
    private User user ;
    
    public InsertIntoThread(SqlSessionFactory sessionFactory, TransactionIsolationLevel isoLevel,User user) {
        super(sessionFactory, isoLevel);
        this.user = user;
    }
   
    public void run() {
        SqlSession sqlSession = sessionFactory.openSession(isoLevel);
        UserMapper userMapper = sessionFactory.getConfiguration().getMapper(UserMapper.class, sqlSession);
        userMapper.insertUser(user);
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
           // logger.error("", e);
        }
        sqlSession.commit();
        sqlSession.close();
    }

}
