package com.example.demo.controller;

import com.example.demo.model.Users;
import com.example.demo.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class RegisterController {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    @Autowired
    public RegisterController(UserService userService, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
    }

    @GetMapping("/register")
    public String showRegisterPage(Model model) {
        model.addAttribute("user", new Users());
        return "register"; // Ссылка на шаблон register.html
    }

    // Обработка регистрации (POST-запрос)
    @PostMapping("/register")
    public String registerUser(@ModelAttribute Users user, Model model, HttpServletRequest request) {
        try {
            if (userService.findByEmail(user.getEmail())) {
                model.addAttribute("error", "Email is already in use");
                return "register";
            }

            // Кодируем пароль
            var originalPassword = user.getPassword();
            user.setPassword(passwordEncoder.encode(originalPassword));

            // Сохраняем пользователя
            userService.addUser(user);

            // Аутентифицируем пользователя после регистрации
            Authentication auth = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(user.getEmail(), originalPassword)
            );

            // Сохраняем аутентификацию в SecurityContext
            SecurityContext context = SecurityContextHolder.createEmptyContext();
            context.setAuthentication(auth);
            SecurityContextHolder.setContext(context);

            // Принудительно сохраняем контекст в сессии
            HttpSession session = request.getSession(true);
            session.setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY, context);

            return "redirect:/vacations";
        } catch (Exception e) {
            model.addAttribute("error", "Registration not successful");
            return "register";
        }
    }


}
