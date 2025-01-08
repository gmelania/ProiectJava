package com.example.demo.controller;

import com.example.demo.model.Users;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/home")
    public String home() {
        return "index";
    }


//    @GetMapping("/vacations")
//    public String vacations() {
//        return "vacations";
//    }
//
//    @GetMapping("/my-vacations")
//    public String myVacations() {
//        return "my-vacations";
//    }
//
//    @GetMapping("/admin")
//    public String admin() {
//        return "admin";
//    }
}
