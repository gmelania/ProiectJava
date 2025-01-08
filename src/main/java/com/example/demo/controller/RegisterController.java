package com.example.demo.controller;

import com.example.demo.model.Users;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class RegisterController {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public RegisterController(UserService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/register")
    public String showRegisterPage(Model model) {
        model.addAttribute("user", new Users());
        return "register"; // Ссылка на шаблон register.html
    }

    // Обработка регистрации (POST-запрос)
    @PostMapping("/register")
    public String registerUser(@ModelAttribute Users user, Model model) {
        if (userService.findByEmail(user.getEmail())) {
            model.addAttribute("error", "Email is already in use");
            return "register"; // Вернуться на страницу регистрации с ошибкой
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        userService.addUser(user);
        return "redirect:/vacations";
    }
}
