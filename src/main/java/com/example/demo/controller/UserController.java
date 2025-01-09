package com.example.demo.controller;

import com.example.demo.model.Users;
import com.example.demo.service.UserService;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    // return all users
    @GetMapping("users/all")
    public List<Users> getAllUsers() {
        return userService.getAllUsers();
    }


}
