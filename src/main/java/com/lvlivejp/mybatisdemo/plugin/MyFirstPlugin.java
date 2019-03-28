package com.lvlivejp.mybatisdemo.plugin;

import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.session.ResultHandler;

import java.sql.Statement;
import java.util.Properties;

@Intercepts({
        @Signature(type = StatementHandler.class,method = "query",args = {Statement.class, ResultHandler.class})
})
public class MyFirstPlugin implements Interceptor {

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        System.out.println("*********************intercept:"+invocation);
        return invocation.proceed();
    }

    @Override
    public Object plugin(Object target) {
        System.out.println("*********************plugin:"+target);
        Object wrap = Plugin.wrap(target, this);
        return wrap;
    }

    @Override
    public void setProperties(Properties properties) {
        System.out.println("properties:"+properties);
    }
}
