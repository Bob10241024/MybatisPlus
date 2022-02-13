package com.xixi;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//扫描我们的mapper文件夹
//@MapperScan("com.xixi.mapper")
@SpringBootApplication
public class MybatisPlusApplication {

    public static void main(String[] args) {

        SpringApplication.run(MybatisPlusApplication.class, args);
    }

}
