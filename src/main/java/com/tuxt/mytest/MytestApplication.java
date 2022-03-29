package com.tuxt.mytest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.tuxt.mytest"})
public class MytestApplication {

    public static void main(String[] args) {
        SpringApplication.run(MytestApplication.class, args);
    }

}
