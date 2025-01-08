package com.example.demo.service;

import com.example.demo.model.Vacation;
import com.example.demo.repository.VacationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VacationService {

    @Autowired
    private VacationRepository vacationRepository;

    public void addVacation(Vacation vacation) {
        vacationRepository.save(vacation);
    }

    public List<Vacation> getAllVacations() {
        return vacationRepository.findAll();
    }


}
