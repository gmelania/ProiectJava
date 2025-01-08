package com.example.demo.controller;

import org.springframework.ui.Model;
import com.example.demo.model.UserVacationsList;
import com.example.demo.model.Users;
import com.example.demo.model.Vacation;
import com.example.demo.service.UserService;
import com.example.demo.service.UserVacationsListService;
import com.example.demo.service.VacationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class VacationController {

    @Autowired
    private VacationService vacationService;

    @Autowired
    private UserService userService;

    @Autowired
    private UserVacationsListService userVacationsListService;

    @GetMapping("/my-vacations")
    public String showVacations(@AuthenticationPrincipal Users user, Model model) {
        Users currentUser = null;
        if(userService.findByEmail(user.getEmail())) //user found
            currentUser = user;

        List<UserVacationsList> userVacationsList = userVacationsListService.getUserVacationListsByUser(currentUser);

        model.addAttribute("userVacationsList", userVacationsList);
        return "my-vacations";
    }

    @PostMapping("/my-vacations")
    public String addVacation(@AuthenticationPrincipal Users user,
                              @RequestParam String destination,
                              @RequestParam String startDate,
                              @RequestParam String endDate,
                              @RequestParam String notes,
                              @RequestParam boolean visited) {
        Users currentUser = null;
        if(userService.findByEmail(user.getEmail())) // user found
            currentUser = user;
        else
            return "redirect:/"; // some error

        Vacation vacation = new Vacation();
        vacation.setDestination(destination);
        vacationService.addVacation(vacation);

        UserVacationsList userVacationsList = new UserVacationsList();
        userVacationsList.setUserEntity(currentUser);
        userVacationsList.setVacationEntity(vacation);
        userVacationsList.setStartDate(startDate);
        userVacationsList.setEndDate(endDate);
        userVacationsList.setNotes(notes);
        userVacationsList.setVisited(visited);
        userVacationsListService.addUserVacationList(userVacationsList);

        return "redirect:/my-vacations";
    }

}
