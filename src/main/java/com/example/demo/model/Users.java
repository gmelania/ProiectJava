package com.example.demo.model;

//import org.springframework.boot.autoconfigure.security.SecurityProperties.User;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "Users")
public class Users {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id; 

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "nume")
    private String nume;

    @Column(name = "prenume")
    private String prenume;

    @Column(name = "tara")
    private String tara;

    @Column(name = "telefon")
    private String telefon;

    // mapping the user to his vacations list
    @OneToMany(mappedBy = "userEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<UserVacationsList> userVacationsList;

    // Constructors, getters and setters
    public Users() {
    }

    public Users(String email, String password, String nume, String prenume, String tara, String telefon) {
        //this.id = id;
        this.email = email;
        this.password = password;
        this.nume = nume;
        this.prenume = prenume;
        this.tara = tara;
        this.telefon = telefon;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public String getNume() {
        return nume;
    }
    public void setNume(String nume) {
        this.nume = nume;
    }

    public String getPrenume() {
        return prenume;
    }
    public void setPrenume(String prenume) {
        this.prenume = prenume;
    }

    public String getTara() {
        return tara;
    }
    public void setTara(String tara) {
        this.tara = tara;
    }

    public String getTelefon() {
        return telefon;
    }
    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public List<UserVacationsList> getUserVacationsList() {
        return userVacationsList;
    }

    public void setUserVacationsList(List<UserVacationsList> userVacationsList) {
        this.userVacationsList = userVacationsList;
    }
}
