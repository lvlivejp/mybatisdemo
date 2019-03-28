package com.lvlivejp.mybatisdemo.config;

import com.lvlivejp.mybatisdemo.plugin.MyFirstPlugin;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MybatisConfig {

    /*
    初始化自定义插件
     */
//    @Bean
    public Interceptor interceptor(){
        return new MyFirstPlugin();
    }

    @Autowired
    private SqlSessionFactory sqlSessionFactory;

    @Bean
    public SqlSession sqlSession(){
        return sqlSessionFactory.openSession(ExecutorType.BATCH);
    }
}
