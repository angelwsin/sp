package com.db.tx;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.TransactionIsolationLevel;

import com.mybatis.mapper.UserMapper;
import com.vo.User;

public class SelectRepeatableReadThread extends DBThread {

    private User user ;
    
    public SelectRepeatableReadThread(SqlSessionFactory sessionFactory, TransactionIsolationLevel isoLevel,User user) {
        super(sessionFactory, isoLevel);
        this.user = user;
    }

    public void run() {
       SqlSession sqlSession =  sessionFactory.openSession(isoLevel);
       UserMapper userMapper = sessionFactory.getConfiguration().getMapper(UserMapper.class, sqlSession);
       User u =  userMapper.findByName(user);
       System.out.println(u!=null?u.getName()+" 提交后可以读到新的数据":"提前读不会读到未提交的数据，但可以读到原来的数据 RepeatableRead");
       
       try {
           Thread.sleep(10000);
       } catch (InterruptedException e) {
           e.printStackTrace();
          // logger.error("", e);
       }
       User ux = new User();
        ux.setName("update");
        u =  userMapper.findByName(ux);
       System.out.println(u!=null?u.getName()+" 提交后可以读到新的数据,两次读到的数据不一致出现不可重复读 RepeatableRead":"提前读不会读到未提交的数据，但可以读到原来的数据");
       sqlSession.commit();
       sqlSession.close();
    }

}
