package com.db.tx;

import java.io.InputStream;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.session.TransactionIsolationLevel;

import com.mybatis.mapper.UserMapper;
import com.vo.User;



public class ReadCommited {
    
    /**
     * 读已提交        可以消除脏读（不会读到未提交的数据）  但是会读到原来的数据  读上不加锁
     *         会出现  不可重复读(同一条数据另一事务修改或删除) 和 幻读（整个表）
     * 
     * @param args
     */
    
    public static void main(String[] args) {
        InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("conf/myBatis_conf.xml");
        SqlSessionFactory  sessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        User user = new User();
        user.setAge(8);
        user.setName("ReadCommit");
        Thread insertThread =  new Thread(new InsertIntoThread(sessionFactory, TransactionIsolationLevel.READ_COMMITTED,user));
        insertThread.start();
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
            //logger.error("", e);
        }
        new Thread(new UpdateThread(sessionFactory, TransactionIsolationLevel.READ_COMMITTED,user)).start();
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
            //logger.error("", e);
        }
        new Thread(new SelectRepeatableReadThread(sessionFactory, TransactionIsolationLevel.READ_COMMITTED,user)).start();
       // new Thread(new SelectReadCommitedThread(sessionFactory, TransactionIsolationLevel.READ_COMMITTED,user)).start();
        
        try {
            Thread.sleep(20000);
        } catch (InterruptedException e) {
           // logger.error("", e);
        }
        //new Thread(new SelectReadCommitedThread(sessionFactory, TransactionIsolationLevel.READ_COMMITTED,user)).start();
        
        SqlSession sqlSession = sessionFactory.openSession();
        UserMapper userMapper = sessionFactory.getConfiguration().getMapper(UserMapper.class, sqlSession);
        userMapper.deleteUser(user);
        user.setName("update");
        userMapper.deleteUser(user);
        sqlSession.commit();
        sqlSession.close();
        System.out.println("清除数据成功");
    }

}
