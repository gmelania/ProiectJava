package com.example.demo.controller;

import ch.qos.logback.core.model.Model;
import com.example.demo.model.Users;
import com.example.demo.model.Vacation;
import com.example.demo.service.UserService;
import java.util.List;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
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

    // add registered user in table Users
    @PostMapping("/register")
    public String registerUser(@RequestParam String email, @RequestParam String password,
                               @RequestParam String nume, @RequestParam String prenume,
                               @RequestParam String tara, @RequestParam String telefon, HttpServletRequest request) {

        if (userService.findByEmail(email)) {
            return "redirect:/register?error=email";
        }

        try{

            Users user = new Users(email, password, nume, prenume, tara, telefon);
            userService.addUser(user);

            // Automatic auth after registration
            UserDetails userDetails = org.springframework.security.core.userdetails.User
                    .withUsername(user.getEmail())
                    .password(user.getPassword())
                    .authorities("USER")
                    .build();
            Authentication auth = new org.springframework.security.authentication.UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(auth);

            // New session
            HttpSession session = request.getSession();
            session.setAttribute("SPRING_SECURITY_CONTEXT", SecurityContextHolder.getContext());

            return "redirect:/vacations";
//          return "redirect:/register?success";
        } catch (Exception e) {
            return "redirect:/register?error=general";
        }
    }

    // Try to log in the user, find by email
    @PostMapping("/login")
    @ResponseBody
    public String loginUser(@RequestParam String email, @RequestParam String password) {
        Users user = userService.findByEmailAndPassword(email, password);
        if(user != null) {

            // Automatic auth after login
            UserDetails userDetails = org.springframework.security.core.userdetails.User
                    .withUsername(user.getEmail())
                    .password(user.getPassword())
                    .authorities("USER")
                    .build();
            Authentication auth = new org.springframework.security.authentication.UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(auth);

            return "Login successful";
        } else {
            return "Invalid email or password";
        }
    }
}
