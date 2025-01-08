package com.example.demo.service;

import com.example.demo.model.Vacation;
import com.example.demo.repository.VacationRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VacationService {

    private final VacationRepository vacationRepository;

    public VacationService(VacationRepository vacationRepository) {
        this.vacationRepository = vacationRepository;
    }

    public List<Vacation> getAllVacations() {
        return vacationRepository.findAll();
    }

    public Optional<Vacation> getVacationById(int id) {
        return vacationRepository.findById(id);
    }

    public List<Vacation> getVacationsByUserId(int userId) {
        return vacationRepository.findByUserId(userId);
    }

    public Vacation addVacation(Vacation vacation) {
        return vacationRepository.save(vacation);
    }

    public void deleteVacationById(int id) {
        vacationRepository.deleteById(id);
    }

    public Optional<Vacation> updateVacation(int id, Vacation updatedVacation) {
        return vacationRepository.findById(id).map(existingVacation -> {
            existingVacation.setDestination(updatedVacation.getDestination());
            existingVacation.setStartDate(updatedVacation.getStartDate());
            existingVacation.setEndDate(updatedVacation.getEndDate());
            existingVacation.setNotes(updatedVacation.getNotes());
            existingVacation.setVisited(updatedVacation.isVisited());
            return vacationRepository.save(existingVacation);
        });
    }
}
