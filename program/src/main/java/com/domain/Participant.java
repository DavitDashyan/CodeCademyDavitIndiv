package com.domain;

public class Participant {

    private String name;
    private String email;
    private String birthdate;
    private String address;
    private String postalCode;
    private String city;
    private String country;
    private String gender;

    public Participant(String email, String name, String birthdate, String gender, String address, String postalCode, String city, String country) {
        this.email = email;
        this.name = name;
        this.birthdate = birthdate;
        this.gender = gender;
        this.address = address;
        this.postalCode = postalCode;
        this.city = city;
        this.country = country;
    }

    public static void main(String[] Args) {
    }

    // Getters en setters
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBirthdate() {
        return this.birthdate;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPostalCode() {
        return this.postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getCity() {
        return this.city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return this.country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGender() {
        return this.gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int moduleProgress() {
        return 0;
    }

    public double webcastPercentage() {
        return 0;
    }

    public String returnAddress() {
        return "";
    }

}
