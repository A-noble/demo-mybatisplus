package com.yuan.demomybatisplus;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.yuan.demomybatisplus.mapper")
public class DemoMybatisplusApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoMybatisplusApplication.class, args);
    }

}
