package com.example.model;
import java.sql.Date;

public class Relative {
    private int id;
    private int userId;
    private String firstname;
    private String lastname;
    private String dateOfBirth;
    private String city;
    private String postalCode;



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

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

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }



    public Relative() {}

    // Constructor with parameters except id
    public Relative(int userId, String firstname, String lastname, String dateOfBirth, String city, String postalCode) {
        this.userId = userId;
        this.firstname = firstname;
        this.lastname = lastname;
        this.dateOfBirth = dateOfBirth;
        this.city = city;
        this.postalCode = postalCode;
    }

    // Getters and setters
    // Note: I've omitted the getters and setters for 'id' since it's an auto-incremented primary key
}
