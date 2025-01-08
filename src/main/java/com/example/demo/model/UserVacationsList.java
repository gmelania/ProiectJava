package com.example.demo.model;

import jakarta.persistence.*;

@Entity
@Table(name = "UserVacationsList")
public class UserVacationsList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userEntity")
    private Users userEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vacationEntity")
    private Vacation vacationEntity;

    @Column(name = "startDate")
    private String startDate;

    @Column(name = "endDate")
    private String endDate;

    @Column(name = "notes")
    private String notes;

    @Column(name = "visited")
    private boolean visited;

    // Getters, Setters and Constructors
    public UserVacationsList() { }

    public UserVacationsList(Users userEntity, Vacation vacation, String startDate, String endDate, String notes, boolean visited) {
//      this.userId = userId;
        this.userEntity = userEntity;
        this.vacationEntity = vacation;
        this.startDate = startDate;
        this.endDate = endDate;
        this.notes = notes;
        this.visited = visited;
    }

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
        this.userEntity = this.userEntity;
    }

    public Vacation getVacationEntity() {
        return vacationEntity;
    }
    public void setVacationEntity(Vacation vacationEntity) {
        this.vacationEntity = this.vacationEntity;
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
