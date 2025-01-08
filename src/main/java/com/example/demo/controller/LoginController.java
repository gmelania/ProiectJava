package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class LoginController {

    private final AuthenticationManager authenticationManager;

    @Autowired
    public LoginController(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @GetMapping("/login")
    public String showLoginPage(Model model) {
        return "login"; // Ссылка на шаблон login.html
    }

    @PostMapping("/login")
    public RedirectView loginUser(@RequestParam String username, @RequestParam String password) {
        try {
            // Аутентификация пользователя
            Authentication auth = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(username, password)
            );

            // Если успешно, перенаправляем на страницу после входа
            return new RedirectView("/vacations");
        } catch (AuthenticationException e) {
            // Если аутентификация не удалась, перенаправляем обратно на страницу логина с ошибкой
            return new RedirectView("/login?error=invalid_credentials");
        }
    }
}
