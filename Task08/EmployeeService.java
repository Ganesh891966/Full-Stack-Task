package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EmployeeService {

    @Autowired
    private Employee employee;

    public void showEmployeeDetails() {
        employee.displayEmployee();
    }
}