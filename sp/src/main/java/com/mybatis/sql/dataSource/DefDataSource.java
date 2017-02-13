package com.mybatis.sql.dataSource;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.sql.DataSource;

import com.utils.StringUtils;

public class DefDataSource  implements DataSource {
    
     private String driver;
     private String username;
     private String password;
     private String url;
    
    
    private static Map<String,Driver>  registDriver = new HashMap<String, Driver>();
    

    public PrintWriter getLogWriter() throws SQLException {
        return DriverManager.getLogWriter();
    }

    public void setLogWriter(PrintWriter out) throws SQLException {
        DriverManager.setLogWriter(out);
    }

    public void setLoginTimeout(int seconds) throws SQLException {
        DriverManager.setLoginTimeout(seconds);
    }

    public int getLoginTimeout() throws SQLException {
        return DriverManager.getLoginTimeout();
    }

    public <T> T unwrap(Class<T> iface) throws SQLException {
        return null;
    }

    public boolean isWrapperFor(Class<?> iface) throws SQLException {
        return false;
    }

    public Connection getConnection() throws SQLException {
        return doConnection(url, init(username,password));
    }

    public Connection getConnection(String username, String password) throws SQLException {
        return doConnection(url, init(username,password));
    }

    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
    
    private Properties init(String username,String password){
        Properties props = new Properties();
        props.put("user",StringUtils.getValue(this.username, username));
        props.put("password", StringUtils.getValue(this.password, password));
        return props;
    }
    
    private Connection doConnection(String url,Properties props){
        if(!registDriver.containsKey(this.driver)){
            try {
              Class<?> clazz =   Class.forName(this.driver);
              Driver d = (Driver) clazz.newInstance();
              DriverManager.registerDriver(d);
              registDriver.put(this.driver, d);
            } catch (Exception e) {
              //  logger.error("", e);
                e.printStackTrace();
            }
        }
        try {
            return DriverManager.getConnection(url, props);
        } catch (SQLException e) {
           // logger.error("", e);
            e.printStackTrace();
        }
        return null;
    }
    
    
    public static void main(String[] args) {
        
    }
    

}
