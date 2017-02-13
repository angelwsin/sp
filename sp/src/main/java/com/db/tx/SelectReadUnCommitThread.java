package com.db.tx;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.TransactionIsolationLevel;

import com.mybatis.mapper.UserMapper;
import com.vo.User;

public class SelectReadUnCommitThread extends DBThread {

    private User user ;
    
    public SelectReadUnCommitThread(SqlSessionFactory sessionFactory, TransactionIsolationLevel isoLevel,User user) {
        super(sessionFactory, isoLevel);
        this.user = user;
    }

    public void run() {
       SqlSession sqlSession =  sessionFactory.openSession(isoLevel);
       UserMapper userMapper = sessionFactory.getConfiguration().getMapper(UserMapper.class, sqlSession);
       try {
           Thread.sleep(1000);
       } catch (InterruptedException e) {
           e.printStackTrace();
          // logger.error("", e);
       }
       User u =  userMapper.findByName(user);
       System.out.println(u!=null?u.getName():"读到的为空");
       sqlSession.commit();
       sqlSession.close();
    }

}
