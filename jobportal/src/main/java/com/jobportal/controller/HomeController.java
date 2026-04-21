package com.jobportal.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/view-jobs")
    public String viewJobsPage() {
        return "view-jobs";
    }
}