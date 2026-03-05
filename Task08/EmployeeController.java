package com.example;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class EmployeeController {

    @RequestMapping("/employee")
    public String showEmployee(Model model) {

        Employee emp = new Employee();

        model.addAttribute("id", 101);
        model.addAttribute("name", "Ganesh");
        model.addAttribute("dept", "IT");

        return "employee";
    }
}