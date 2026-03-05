package com.example;

import org.springframework.stereotype.Component;

@Component
public class Employee {

    private int id = 101;
    private String name = "Ganesh";
    private String department = "IT";

    public void displayEmployee() {
        System.out.println("Employee ID: " + id);
        System.out.println("Name: " + name);
        System.out.println("Department: " + department);
    }

}
