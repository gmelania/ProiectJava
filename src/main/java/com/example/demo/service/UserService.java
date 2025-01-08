package com.example.demo.service;

import com.example.demo.model.Users;
import com.example.demo.repository.UsersRepository;
import java.util.List;
import org.springframework.stereotype.Service;


@Service
public class UserService {

    private final UsersRepository usersRepository;

    public UserService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    // call repo to hire user
    public void addUser(Users user) {
        usersRepository.save(user);
    }

    // call repo to rescue user by email and passwd
    public Users findByEmailAndPassword(String email, String password) {
        return usersRepository.findByEmailAndPassword(email, password); // chemam repo sa caute userul in tabel
    }

    // call repo to rescue user by email
    public boolean findByEmail(String email) {
        List<Users> users = usersRepository.findByEmail(email);
        return !users.isEmpty(); // returneaza 1 daca lista de useri cu acelasi email nu e empty
    }

    // call repo to line up all users
    public List<Users> getAllUsers() {
        return usersRepository.findAll();
    }
}
