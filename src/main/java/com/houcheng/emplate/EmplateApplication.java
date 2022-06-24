package com.houcheng.emplate;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.houcheng.emplate.mapper")
public class EmplateApplication {

    public static void main(String[] args) {
        SpringApplication.run(EmplateApplication.class, args);
    }

}
