package com.example.demo.controller;

import com.example.demo.model.Users;
import com.example.demo.model.Vacation;
import com.example.demo.service.UserService;
import com.example.demo.service.VacationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.Optional;

@Controller
public class VacationController {

    private final UserService usersService;
    private final VacationService vacationService;

    @Autowired
    public VacationController(UserService usersService, VacationService vacationService) {
        this.usersService = usersService;
        this.vacationService = vacationService;
    }

    // Отображение всех отпусков пользователя
    @GetMapping("/vacations")
    public String getUserVacationsList(Model model) {
        // Получаем текущего пользователя из SecurityContext
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName();  // Получаем имя пользователя (email)

        Users currentUser = usersService.getUserByEmail(email);

        // Получаем список всех отпусков этого пользователя
        model.addAttribute("userVacationLists", vacationService.getVacationsByUserId(currentUser.getId()));

        return "vacations";  // Отображаем шаблон с отпусками
    }

    // Добавить новый отпуск
    @PostMapping("/vacations")
    public RedirectView addVacation(
            @RequestParam String destination,
            @RequestParam String startDate,
            @RequestParam String endDate,
            @RequestParam(required = false) String notes,
            @RequestParam(required = false) boolean visited) {

        // Получаем текущего пользователя из SecurityContext
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName();  // Получаем имя пользователя (email)

        Users currentUser = usersService.getUserByEmail(email);

        // Создаем новый отпуск
        Vacation newVacation = new Vacation();
        newVacation.setDestination(destination);
        newVacation.setStartDate(startDate);
        newVacation.setEndDate(endDate);
        newVacation.setNotes(notes);
        newVacation.setVisited(visited);
        newVacation.setUser(currentUser);

        // Сохраняем новый отпуск
        vacationService.addVacation(newVacation);

        // Перенаправляем на страницу с отпусками
        return new RedirectView("/vacations");
    }

    @PatchMapping("/vacations")
    public ResponseEntity<?> editVacation(
            @RequestParam(required = false) Integer id, // ID отпуска для обновления
            @RequestParam(required = false) String destination,
            @RequestParam(required = false) String startDate,
            @RequestParam(required = false) String endDate,
            @RequestParam(required = false) String notes,
            @RequestParam(required = false) boolean visited) {

        // Получаем отпуск по ID
        Optional<Vacation> vacationOpt = vacationService.getVacationById(id);

        // Если отпуск найден
        if (vacationOpt.isPresent()) {
            Vacation vacation = vacationOpt.get();

            // Обновляем поля отпуска
            if (destination != null) {
                vacation.setDestination(destination);
            }
            if (startDate != null) {
                vacation.setStartDate(startDate);
            }
            if (endDate != null) {
                vacation.setEndDate(endDate);
            }
            if (notes != null) {
                vacation.setNotes(notes);
            }
            vacation.setVisited(visited);

            // Сохраняем обновленный отпуск
            vacationService.updateVacation(id, vacation);

            // Возвращаем успешный ответ с кодом 200 (OK)
            return ResponseEntity.ok(vacation); // Возвращаем обновленный отпуск в ответе
        } else {
            // Возвращаем ошибку 404, если отпуск не найден
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Vacation not found");
        }
    }




    @GetMapping("/vacations/delete/{id}")
    public String deleteVacation(@PathVariable("id") Integer id) {
        vacationService.deleteVacationById(id); // Удаление отпуска
        return "redirect:/vacations"; // Перенаправление на страницу со списком отпусков
    }

    @GetMapping("/vacations/edit/{id}")
    @ResponseBody
    public Vacation getVacationById(@PathVariable("id") Integer id) {
        Optional<Vacation> vacation = vacationService.getVacationById(id);
        return vacation.orElse(null);  // Возвращаем отпуск или null, если не найден
    }

}
