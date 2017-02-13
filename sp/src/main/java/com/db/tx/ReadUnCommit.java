package com.db.tx;

import java.io.InputStream;
import java.util.concurrent.CountDownLatch;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.session.TransactionIsolationLevel;

import com.mybatis.mapper.UserMapper;
import com.vo.User;

public class ReadUnCommit {
    
    /**
     * 读到了InsertIntoThread 没有提交的数据 出现脏读的现象
     * 
     * @param args
     */
    
    public static void main(String[] args) {
        InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("conf/myBatis_conf.xml");
        SqlSessionFactory  sessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        User user = new User();
        user.setAge(8);
        user.setName("ReadUnCommit");
        Thread insertThread =  new Thread(new InsertIntoThread(sessionFactory, TransactionIsolationLevel.READ_COMMITTED,user));
        insertThread.start();
        new Thread(new SelectReadUnCommitThread(sessionFactory, TransactionIsolationLevel.READ_UNCOMMITTED,user)).start();
        
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
           // logger.error("", e);
        }
        SqlSession sqlSession = sessionFactory.openSession();
        UserMapper userMapper = sessionFactory.getConfiguration().getMapper(UserMapper.class, sqlSession);
        userMapper.deleteUser(user);
        sqlSession.commit();
        sqlSession.close();
        
    }

}
