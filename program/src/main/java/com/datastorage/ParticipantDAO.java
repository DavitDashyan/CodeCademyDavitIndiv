package com.datastorage;

import java.sql.*;
import java.time.LocalDate;
import org.verdictdb.commons.DBTablePrinter;
import com.domain.Participant;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ParticipantDAO {

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

        printParticipant();
    }

    public static void printParticipant() {
        System.out.println("Print Participant Called");
        try { Connection db = SQLServerDatabase.getDatabase().getConnection();
            // Prints full table of Participant
            PreparedStatement statement = db.prepareStatement("SELECT * FROM Participant;");
            ResultSet result = statement.executeQuery();
            DBTablePrinter.printResultSet(result);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static ObservableList<Participant> getParticipants() {
        System.out.println("Get Participant Called");
        ObservableList<Participant> participants = FXCollections.observableArrayList();
        try {
            Connection conn = SQLServerDatabase.getDatabase().getConnection();
            String query = "SELECT * FROM Participant;";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);

            while (rs.next()) {
                Participant participant = new Participant(
                        rs.getString("EmailAddress"),
                        rs.getString("Name"),
                        rs.getDate("BirthDate").toString(),
                        rs.getString("Gender"),
                        rs.getString("Address"),
                        rs.getString("PostalCode"),
                        rs.getString("City"),
                        rs.getString("Country"));

                participants.add(participant);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return participants;
    }

    public static void insertParticipant(String email, String name, LocalDate birthdate, String gender, String address, String postalCode, String city, String country) {
        System.out.println("Insert Participant Called");
        try {
            Connection conn = SQLServerDatabase.getDatabase().getConnection();
            String query = "INSERT INTO PARTICIPANT VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
    
            String birthdateStr = String.valueOf(birthdate.getYear()) + "-" + String.valueOf(birthdate.getMonthValue()) + "-" + String.valueOf(birthdate.getDayOfMonth());
   
            PreparedStatement stm = conn.prepareStatement(query);
    
            stm.setString(1, email);
            stm.setString(2, name);
            stm.setDate(3, Date.valueOf(birthdateStr));
            stm.setString(4, gender);
            stm.setString(5, address);
            stm.setString(6, postalCode);
            stm.setString(7, city);
            stm.setString(8, country);
    
            stm.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void updateParticipant(String email, String name, Date birthdate, String gender, String address, String postalCode, String city, String country) {
        System.out.println("Update Participant Called");
        try {
            Connection conn = SQLServerDatabase.getDatabase().getConnection();
            String query = "UPDATE Participant SET Name=?, Birthdate=?, Gender=?, Address=?, PostalCode=?, City=?, Country=? WHERE EmailAddress=?";
            
            try (PreparedStatement stm = conn.prepareStatement(query)) {
                stm.setString(1, name);
                stm.setDate(2, birthdate);
                stm.setString(3, gender);
                stm.setString(4, address);
                stm.setString(5, postalCode);
                stm.setString(6, city);
                stm.setString(7, country);
                stm.setString(8, email);

                stm.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void deleteParticipant(String email) {
        System.out.println("Delete Participant Called");
    
        Connection conn = SQLServerDatabase.getDatabase().getConnection();
        String query = "DELETE FROM Participant where EmailAddress = ?";
    
        try {
            PreparedStatement stm = conn.prepareStatement(query);
            stm.setString(1, email);
    
            stm.execute();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    
    // Voor de detailpagina van participant
    public static ObservableList<Participant> getParticipantByEmail(String email) {
        System.out.println("Get Participant by specific email Called");

        ObservableList<Participant> participants = FXCollections.observableArrayList();
        try {
            Connection db = SQLServerDatabase.getDatabase().getConnection();
            PreparedStatement statement = db.prepareStatement("SELECT * FROM Participant WHERE EmailAddress = ?;");
            statement.setString(1, email);
            ResultSet result = statement.executeQuery();

            while (result.next()) {
                Participant participant = new Participant(
                        result.getString("EmailAddress"),
                        result.getString("Name"),
                        result.getDate("BirthDate").toString(),
                        result.getString("Gender"),
                        result.getString("Address"),
                        result.getString("PostalCode"),
                        result.getString("City"),
                        result.getString("Country"));

                participants.add(participant);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return participants;
    }

    public static ObservableList<String> getEmails() {
        System.out.println("Participant List of Emails Called");

        ObservableList<String> emailsList = FXCollections.observableArrayList();
        Connection conn = SQLServerDatabase.getDatabase().getConnection();
        String query = "SELECT EmailAddress FROM Participant;";
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);

            while (rs.next()) {
                emailsList.add(rs.getString("EmailAddress"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return emailsList;
    }   
}
