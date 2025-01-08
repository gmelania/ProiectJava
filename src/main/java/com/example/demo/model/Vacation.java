package com.example.demo.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "Vacation")
public class Vacation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "destination")
    private String destination;

    @Column(name = "startDate")
    private String startDate;

    @Column(name = "endDate")
    private String endDate;

    @Column(name = "notes")
    private String notes;

    @OneToMany(mappedBy = "vacationEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<UserVacationsList> userVacationsList;

    // Getters, Setters and Constructors
    public Vacation() {

    }

    public Vacation(String destination, String start, String end, String notes, List<UserVacationsList> userVacationsList) {
        this.destination = destination;
        this.startDate = start;
        this.endDate = end;
        this.notes = notes;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getDestination() { return destination; }
    public void setDestination(String destination) { this.destination = destination; }

    public String getNotes() {
        return notes;
    }
    public void setNotes(String notes) {}

    public String getStartDate() {
        return startDate;
    }
    public void setStartDate(String startDate) {}

    public String getEndDate() {
        return endDate;
    }
    public void setEndDate(String endDate) {}
}
