package com.tuxt.mytest;

import com.alibaba.fastjson.JSON;
import com.tuxt.mytest.test.Employee;

public class EmployeeTest {
    public static void main(String[] args) {
        Employee employee=new Employee();
        employee.setName("a");
        employee.setAge(10);
        Employee.Address address=new Employee.Address();
        address.setRegion("b");
        address.setStreet("b-1");

        System.out.println(JSON.toJSON(address));
    }
}
