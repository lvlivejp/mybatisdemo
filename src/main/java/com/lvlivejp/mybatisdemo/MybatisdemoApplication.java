package com.lvlivejp.mybatisdemo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.lvlivejp.mybatisdemo.mapper")
//@tk.mybatis.spring.annotation.MapperScan("com.lvlivejp.mybatisdemo.mapper.common")
public class MybatisdemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(MybatisdemoApplication.class, args);
    }
}
