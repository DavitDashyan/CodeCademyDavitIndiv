package com.datastorage;

import java.sql.*;
import java.time.LocalDateTime;
import com.domain.AmountSeen;
import com.domain.Certificate;
import com.domain.ContentItem;
import com.domain.ParticipantProgress;
import com.domain.Registration;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ParticipantProgressDAO {

    String empNo;
    Connection conn;
    Statement stmt;
    ResultSet rs;

    public static void main(String[] args) {
        try{
            SQLServerDatabase.getDatabase().connect();
             System.out.println("connected!");
         } catch (SQLException e) {
             e.printStackTrace();
         }
    }

    // List of Registration of a specific Participant
    public static ObservableList<Registration> getRegistrationsByEmail(String emailAddress) {
        System.out.println("Get Registrations of a Participant");
            ObservableList<Registration> registrationList = FXCollections.observableArrayList();
        
            try {
                Connection db = SQLServerDatabase.getDatabase().getConnection();
                PreparedStatement statement = db.prepareStatement("SELECT * FROM Registration WHERE EmailAddress = ?");
                statement.setString(1, emailAddress);
                ResultSet result = statement.executeQuery();
        
                while (result.next()) {
                        // Retrieve and add the CertificaatNaam (course name)
                        String courseName = result.getString("CourseName");
                        String date = result.getString("RegistrationDate");

                    registrationList.add(new Registration(emailAddress, courseName, date));
                }
    
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            } 
            return registrationList;
    }

    // Overview 4
    // To retrieve certificates of a specific Participant
    public static ObservableList<Certificate> getCertificatesForEmail(String emailAddress) {
        System.out.println("Get Certificates of a Participant");
        ObservableList<Certificate> certificatesList = FXCollections.observableArrayList();

        try {
            Connection db = SQLServerDatabase.getDatabase().getConnection();

            String query = "SELECT CourseName, CertificateDate FROM Certificate WHERE EmailAddress = ?";
            PreparedStatement statement = db.prepareStatement(query);
            statement.setString(1, emailAddress);

            ResultSet result = statement.executeQuery();

            while (result.next()) {
                String courseName = result.getString("CourseName");
                String date = result.getString("CertificateDate");
                certificatesList.add(new Certificate(courseName, date));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return certificatesList;
    }

    // Overview 4
    // To retrieve participant progress of a specific participant by email
    public static ObservableList<ParticipantProgress> getParticipantProgressByEmail(String emailAddress) {
        System.out.println("Get Progress of a Participant");

        ObservableList<ParticipantProgress> participantProgressesList = FXCollections.observableArrayList();

        try {

            Connection conn = SQLServerDatabase.getDatabase().getConnection();
            PreparedStatement statement = conn.prepareStatement("SELECT R.EmailAddress, R.CourseName, CI.ModuleTitle, CI.ModuleVersion, CI.WebcastTitle, ASV.ViewPercentage " +
                        "FROM Registration R " +
                        "JOIN ContentItem CI ON R.CourseName = CI.CourseName " +
                        "LEFT JOIN AmountSeen ASV ON R.EmailAddress = ASV.EmailAddress AND CI.ContentItemID = ASV.ContentItemID " +
                        "WHERE R.EmailAddress = ? " +
                        "ORDER BY R.CourseName");

                statement.setString(1, emailAddress);
                ResultSet result = statement.executeQuery();

                while (result.next()) {
                        String registrationEmailAddress = result.getString("EmailAddress");
                        String courseName = result.getString("CourseName");
                        String moduleTitle = result.getString("ModuleTitle");
                        String moduleVersion = result.getString("ModuleVersion");
                        String webcastTitle = result.getString("WebcastTitle");
                        double viewPercentage = result.getDouble("ViewPercentage");

                        Registration registration = new Registration(registrationEmailAddress, courseName);
                        ContentItem contentItem = new ContentItem(moduleTitle, moduleVersion, webcastTitle);
                        AmountSeen amountSeen = new AmountSeen(registrationEmailAddress, viewPercentage);

                        participantProgressesList.add(new ParticipantProgress(registration, contentItem, amountSeen));
                }

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return participantProgressesList;
    }

    public static void insertCertificate(String emailAddress, String courseName, String date) {
        try {
            Connection conn = SQLServerDatabase.getDatabase().getConnection();
            String insertQuery = "INSERT INTO Certificate (EmailAddress, CourseName, CertificateDate) VALUES (?, ?, ?)";
            String deleteQuery = "DELETE FROM Registration WHERE EmailAddress = ? AND CourseName = ? AND RegistrationDate = ?";
       
            PreparedStatement stmInsert = conn.prepareStatement(insertQuery);

            stmInsert.setString(1, emailAddress);
            stmInsert.setString(2, courseName);
            stmInsert.setTimestamp(3, Timestamp.valueOf(LocalDateTime.now()));

            PreparedStatement stmDelete = conn.prepareStatement(deleteQuery);
            stmDelete.setString(1, emailAddress);
            stmDelete.setString(2, courseName);
            stmDelete.setString(3, date);

            stmInsert.execute();
            stmDelete.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}
