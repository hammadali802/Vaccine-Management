package com.example.model;

import com.example.model.Relative;
import com.example.model.User;

public class Appointment {
    private int id;
    private int userId;
    private Integer relativeId;
    private String vaccinationCentre;
    private String vaccine;
    private String date;
    private String time;
    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    private String firstname;
    private String lastname;

    public User getUser() {
        return user;
    }

    public Relative getRelative() {
        return relative;
    }

    private User user;

    private Relative relative;
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getRelativeId() {
        return relativeId;
    }

    public void setRelativeId(int relativeId) {
        this.relativeId = relativeId;
    }

    public String getVaccinationCentre() {
        return vaccinationCentre;
    }

    public void setVaccinationCentre(String vaccinationCentre) {
        this.vaccinationCentre = vaccinationCentre;
    }

    public String getVaccine() {
        return vaccine;
    }

    public void setVaccine(String vaccine) {
        this.vaccine = vaccine;
    }

    public String getDate() {
        return date;
    }

    public void setDate( String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }



    public Appointment() {}

    // Constructor with parameters except id
    public Appointment(int userId, Integer relativeId, String vaccinationCentre, String vaccine, String date, String time) {
        this.userId = userId;
        this.relativeId = relativeId;
        this.vaccinationCentre = vaccinationCentre;
        this.vaccine = vaccine;
        this.date = date;
        this.time = time;

//    } public Appointment(int userId,  String vaccinationCentre, String vaccine, String date, String time) {
//        this.userId = userId;
//
//        this.vaccinationCentre = vaccinationCentre;
//        this.vaccine = vaccine;
//        this.date = date;
//        this.time = time;
//    }

        // Getters and setters
        // Note: I've omitted the getters and setters for 'id' since it's an auto-incremented primary key
    }


    public void setUser(User user) {
    }


    public void setRelative(Relative relative) {
    }
}
