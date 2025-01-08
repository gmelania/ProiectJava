package com.example.demo.model;

import jakarta.persistence.*;

@Entity
@Table(name = "UserVacationsList")
public class UserVacationsList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private Users userEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vacation_id", nullable = false)
    private Vacation vacationEntity;

    @Column(name = "startDate")
    private String startDate;

    @Column(name = "endDate")
    private String endDate;

    @Column(name = "notes")
    private String notes;

    @Column(name = "visited")
    private boolean visited;

    public UserVacationsList() {
    }

    public UserVacationsList(Users userEntity, Vacation vacationEntity, String startDate, String endDate, String notes, boolean visited) {
        this.userEntity = userEntity;
        this.vacationEntity = vacationEntity;
        this.startDate = startDate;
        this.endDate = endDate;
        this.notes = notes;
        this.visited = visited;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Users getUserEntity() {
        return userEntity;
    }

    public void setUserEntity(Users userEntity) {
        this.userEntity = userEntity;
    }

    public Vacation getVacationEntity() {
        return vacationEntity;
    }

    public void setVacationEntity(Vacation vacationEntity) {
        this.vacationEntity = vacationEntity;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public boolean isVisited() {
        return visited;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }
}