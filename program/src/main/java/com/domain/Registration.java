package com.domain;

public class Registration {

    private String email;
    private String courseName;
    private String registrationDate;

    public Registration(String email, String courseName, String registrationDate) {
        this.email = email;
        this.courseName = courseName;
        this.registrationDate = registrationDate;
    }

    public Registration(String emailAddress, String courseName) {
        this.email = emailAddress;
        this.courseName = courseName;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCourseName() {
        return this.courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getRegistrationDate() {
        return this.registrationDate;
    }

    public void setRegistrationDate(String date) {
        this.registrationDate = date;
    }


}
