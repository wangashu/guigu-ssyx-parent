package com.wangashu;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = "com.wangashu")
public class ServiceSysAppMain {

    public static void main(String[] args) {
        SpringApplication.run(ServiceSysAppMain.class);
    }
}
