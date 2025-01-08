package com.example.demo.repository;

import com.example.demo.model.UserVacationsList;
import com.example.demo.model.Users;
import com.example.demo.model.Vacation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserVacationsListRepository extends JpaRepository<UserVacationsList, Long> {

    List<UserVacationsList> findByUser(Users user);
}
