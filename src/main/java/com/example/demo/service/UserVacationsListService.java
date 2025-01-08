package com.example.demo.service;

import java.util.List;

import com.example.demo.model.UserVacationsList;
import com.example.demo.model.Users;
import com.example.demo.repository.UserVacationsListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserVacationsListService {

    private UserVacationsListRepository userVacationsListRepository;

    public UserVacationsListService(UserVacationsListRepository userVacationsListRepository) {
        this.userVacationsListRepository = userVacationsListRepository;
    }

    public void addUserVacationList(UserVacationsList userVacationList) {
        userVacationsListRepository.save(userVacationList);
    }

    public List<UserVacationsList> getUserVacationListsByUser(Users user) {
        return userVacationsListRepository.findByUserEntity(user);
    }
}
