package com.example.demo;

import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
@MapperScan(basePackages = "com.example.demo.dao")
public class DemoApplication {

    public static void main(String[] args) {
        // use logging
        Logger logger = LoggerFactory.getLogger(DemoApplication.class);
        logger.info("Spring boot 3");
        SpringApplication.run(DemoApplication.class, args);

    }

}
