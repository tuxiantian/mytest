package com.tuxt.mytest.test;

import lombok.Data;

@Data
public class Employee {

    private String name;
    private Integer age;

    @Data
    public static class Address{
        private String region;
        private String street;
    }
}
