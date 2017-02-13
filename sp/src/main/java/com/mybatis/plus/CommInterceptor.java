package com.mybatis.plus;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;
import java.util.Properties;

import org.apache.ibatis.executor.resultset.ResultSetHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;
import org.springframework.util.CollectionUtils;

import com.mybatis.DBType;
import com.mybatis.Paging;

@Intercepts({
    @Signature(type = StatementHandler.class, method = "prepare", args = {Connection.class,Integer.class}),  
    @Signature(type = ResultSetHandler.class, method = "handleResultSets", args = {Statement.class})
    })
public class CommInterceptor  implements Interceptor{

    private final static String PAGING ="Paging";
    public Object intercept(Invocation invocation) throws Throwable {
        Object obj =  invocation.getTarget();
        if(obj instanceof StatementHandler){
            StatementHandler statementHandler = (StatementHandler) invocation.getTarget();
            BoundSql boundSql =  statementHandler.getBoundSql();
            //使用ongl表达式
            MetaObject metaStatementHandler = SystemMetaObject.forObject(statementHandler);
            MappedStatement mappedStatement=(MappedStatement) metaStatementHandler.getValue("delegate.mappedStatement");
            String selectId=mappedStatement.getId();
            if(selectId.lastIndexOf(PAGING)>0){
                String sql  = boundSql.getSql();
                String countSql =  countSql(sql);
                Connection connection = (Connection) invocation.getArgs()[0];
                PreparedStatement preStm = connection.prepareStatement(countSql);
                List<ParameterMapping> mappings = boundSql.getParameterMappings();
                if(!CollectionUtils.isEmpty(mappings)){
                 statementHandler.getParameterHandler().setParameters(preStm);
                }
                ResultSet countResult =  preStm.executeQuery();
                Paging<?> paging =  (Paging<?>) boundSql.getParameterObject();
                if(countResult.next()){
                paging.setTotalPage(countResult.getLong(1));
                }
               String dataProduct = connection.getMetaData().getDatabaseProductName();
               if(DBType.MySQL.name().equals(dataProduct)){
                 metaStatementHandler.setValue("delegate.boundSql.sql", pagingMySql(sql,paging));
               } else if(DBType.ORACLE.name().equals(dataProduct)){
                 metaStatementHandler.setValue("delegate.boundSql.sql", pagingOracle(sql,paging));
               }
            }
            
        }
       
        return invocation.proceed();
    }

    /**应用拦截点
     * Executor
     * ParameterHandler
     * ResultSetHandler
     * StatementHandler
     * @see org.apache.ibatis.plugin.Interceptor#plugin(java.lang.Object)
     */
    public Object plugin(Object target) {
         if(target instanceof StatementHandler){
             //使用动态代理  对象调用intercept 方法
             return Plugin.wrap(target, this);
         }
         
         return target;
    }

    public void setProperties(Properties properties) {
    }
    
    private String countSql(String sql){
        int fromIndex = sql.toLowerCase().indexOf("from");
         return "select count(0) "+sql.substring(fromIndex);
    }
    
    private String pagingMySql(String sql,Paging<?> paging){
           return  sql+" limit "+paging.getOffset()+" , "+paging.getPageSize();
    }
    
    private String pagingOracle(String sql,Paging<?> paging){
          
          int selectIndex =  sql.toLowerCase().indexOf("select");
          int formIndex = sql.toLowerCase().indexOf("from");
          int whereInex = sql.toLowerCase().indexOf("where");
          int orderIndex = sql.toLowerCase().indexOf("order");
          String where = " where ROWNUM <= "+(paging.getOffset()+paging.getPageSize());
          String table = sql.substring(formIndex+4);
          if(whereInex>0){
              where = where+" and "+sql.substring(whereInex+5);
              table = sql.substring(formIndex+4,whereInex);
              
          }else{
             if(orderIndex<sql.toLowerCase().indexOf("by")){
                 table = sql.substring(formIndex+4,orderIndex);
                 where = where+sql.substring(orderIndex);
             }
          }
         String select =  sql.substring(selectIndex+6, formIndex);
        return "select "+select+" from (select ROWNUM AS rowno,"+select+" from "+table+where+") t where t.rowno >= "+paging.getOffset();
    }

}
