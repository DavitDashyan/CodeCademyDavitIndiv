package com.logic.Validation;

import java.time.LocalDate;

import javafx.scene.control.Alert;

public class InputValidation {

    public static void showInformation(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public static  void showError(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public static boolean isValidURL(String url) {
        // Check if the URL starts with "https://" or "http://" or "www." and contains at least one letter in each section
        String urlRegex = "^(https?://|www\\.)[A-Za-z]+\\.[A-Za-z]+$";
        return url.matches(urlRegex);
    }        

    public static boolean isValidEmail(String email) {
        // Check if the email matches the specified format
        // At least one letter before and after '@', and at least one letter after '.'
        String emailRegex = "^[A-Za-z]+[A-Za-z0-9]*@[A-Za-z]+\\.[A-Za-z]+$";
        return email.matches(emailRegex);
    }

    public static boolean isValidPercentage(String percentage) {
        // Check if percentage is between 0 and 100
        try {
            int value = Integer.parseInt(percentage);
            return value >= 0 && value <= 100;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static boolean isValidDate(int day, int month, int year) {
        // Check if date is correct and not in the future
        try {
            LocalDate inputDate = LocalDate.of(year, month, day);
            
            LocalDate currentDate = LocalDate.now();
            if (inputDate.isAfter(currentDate)) {
                return false;
            }

            return true;
        } catch (java.time.DateTimeException e) {
            return false;
        }
    }

    public static boolean isValidGrade(int grade) {
        // Check if grade is between 1 and 10
        return grade >= 1 && grade <= 10;
    }

    public static boolean isValidDutchPostalCode(String postalCode) {
        if (postalCode == null) { return false; }
        // Check if the postal code matches the specified Dutch format
        // nnnn<spatie>MM, where nnnn is 4 numeric digits, the first digit is not 0,
        // followed by exactly one space, and then two uppercase letters
        String postalCodeRegex = "^[1-9][0-9]{3}\\s[A-Z]{2}$";
        return postalCode.trim().matches(postalCodeRegex);
    }

}
