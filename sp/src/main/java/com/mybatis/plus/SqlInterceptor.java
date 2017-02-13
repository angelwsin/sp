package com.mybatis.plus;

import java.util.Properties;

import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.executor.resultset.ResultSetHandler;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;

@Intercepts({
    @Signature(type = Executor.class, method = "query", args = {MappedStatement.class,Object.class,RowBounds.class,ResultHandler.class}),  
    @Signature(type = Executor.class, method = "update", args = {MappedStatement.class,Object.class})
    })
public class SqlInterceptor implements Interceptor{

    public Object intercept(Invocation invocation) throws Throwable {
        long s = System.currentTimeMillis();
        MappedStatement args = (MappedStatement) invocation.getArgs()[0];
        Object result =  invocation.proceed();
        System.out.printf("%s-%s:%d ms\n", invocation.getMethod().getName(),args.getBoundSql(invocation.getArgs()[1]).getSql(),System.currentTimeMillis()-s);
        return result;
    }

    public Object plugin(Object target) {
        if(target instanceof Executor){
            return Plugin.wrap(target, this);
        }
        return target;
        
    }

    public void setProperties(Properties properties) {
    }

}
