package com.db.tx;

import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.TransactionIsolationLevel;

public abstract class DBThread implements Runnable{

    protected SqlSessionFactory  sessionFactory;
    protected TransactionIsolationLevel isoLevel;
    
    
    public DBThread(SqlSessionFactory  sessionFactory,TransactionIsolationLevel isoLevel){
         this.sessionFactory = sessionFactory;
         this.isoLevel = isoLevel;
    }
}
