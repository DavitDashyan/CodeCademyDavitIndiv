package com.datastorage;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import org.verdictdb.commons.DBTablePrinter;
import com.domain.Registration;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class RegistrationDAO {
    
    public static void main(String[] args) {
        try{
            SQLServerDatabase.getDatabase().connect();
             System.out.println("connected!");
         } catch (SQLException e) {
             e.printStackTrace();
         }

        printRegistration();
    }

    public static void printRegistration() {
        System.out.println("Print Registration Called");

        try { Connection db = SQLServerDatabase.getDatabase().getConnection();
            // Prints full table of Participant
            PreparedStatement statement = db.prepareStatement("SELECT * FROM Registration;");
            ResultSet result = statement.executeQuery();
            DBTablePrinter.printResultSet(result);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static ObservableList<Registration> getRegistrations() {
        System.out.println("Get Registrations Called");

        ObservableList<Registration> registrations = FXCollections.observableArrayList();
        Connection conn = SQLServerDatabase.getDatabase().getConnection();
        String query = "SELECT * FROM Registration;";
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);

            while (rs.next()) {
                Registration registration = new Registration(
                    rs.getString("EmailAddress"),
                    rs.getString("CourseName"),
                    rs.getDate("RegistrationDate").toString());
                registrations.add(registration);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return registrations;
    }

    public static void insertRegistration(String email, String courseName, String date) {
        System.out.println("Insert Registration Called");

        Connection conn = SQLServerDatabase.getDatabase().getConnection();
        String query = "INSERT INTO Registration VALUES (?, ?, ?)";
        try {
            PreparedStatement stm = conn.prepareStatement(query);
            stm.setString(1, email);
            stm.setString(2, courseName);
            stm.setDate(3, Date.valueOf(date));
            stm.execute();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void deleteRegistration(String email, String courseName, String date) {
        System.out.println("Delete Registration Called");

        Connection conn = SQLServerDatabase.getDatabase().getConnection();
        String query = "DELETE FROM Registration WHERE EmailAddress = ? AND CourseName = ? And RegistrationDate = ?";
        try {
            PreparedStatement stm = conn.prepareStatement(query);
            stm.setString(1, email);
            stm.setString(2, courseName);
            stm.setDate(3, Date.valueOf(date));
            stm.execute();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void updateRegistration(String email, String courseName, Date date) {
        System.out.println("Update Registration Called");
    
        Connection conn = SQLServerDatabase.getDatabase().getConnection();
        String query = "UPDATE Registration SET RegistrationDate = ? WHERE EmailAddress = ? AND CourseName = ?";
        
        try {
            PreparedStatement stm = conn.prepareStatement(query);
    
            stm.setDate(1, date);
            stm.setString(2, email);
            stm.setString(3, courseName);
    
            stm.execute();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
}
