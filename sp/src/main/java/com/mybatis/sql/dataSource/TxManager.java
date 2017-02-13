package com.mybatis.sql.dataSource;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

public class TxManager {
    
    
    private DataSource dataSource;
    
    private Connection connection;

    public DataSource getDataSource() {
        return dataSource;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }
    
    
    public Connection getConnection(boolean autoCommit) throws SQLException{
        connection =   dataSource.getConnection();
        connection.setAutoCommit(autoCommit);
        return connection;
    }  
    
    
    public void commit() throws SQLException{
        if(connection!=null&&!connection.getAutoCommit()){
            connection.commit();
        }
    }
    
   public void rollback() throws SQLException{
        if(connection!=null&&!connection.getAutoCommit()){
            connection.rollback();
        }
        
    }
   
   public void close() throws SQLException{
       if(connection!=null){
           connection.setAutoCommit(true);
           connection.close();
       }
   }
    

}
