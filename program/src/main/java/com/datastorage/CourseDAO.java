package com.datastorage;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.verdictdb.commons.DBTablePrinter;
import com.domain.Course;
import com.domain.Difficulty;
import com.domain.Module;

public class CourseDAO {

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

    public static void printCourse() {
        try { Connection db = SQLServerDatabase.getDatabase().getConnection();
            PreparedStatement statement = db.prepareStatement("SELECT * FROM Course;");
            ResultSet result = statement.executeQuery();
            DBTablePrinter.printResultSet(result);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println("\n Course table printed!");
        }
    }


    public static ObservableList<Course> getCourse() {
        System.out.println("Get Course Called");

        ObservableList<Course> courses = FXCollections.observableArrayList();
        try {
            Connection conn = SQLServerDatabase.getDatabase().getConnection();
            String query = "SELECT * FROM Course;";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);

            while (rs.next()) {
                Course course = new Course(
                        rs.getString("CourseName"),
                        rs.getInt("courseNumber"),
                        rs.getString("Subject"),
                        rs.getString("IntroductionText"),
                        Difficulty.valueOf(rs.getString("Difficulty")));

                courses.add(course);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return courses;
    }


    public static ObservableList<String> getCourseNames() {
        System.out.println("Get all CourseNames Called");

        ObservableList<String> courseNamesList = FXCollections.observableArrayList();
        Connection conn = SQLServerDatabase.getDatabase().getConnection();
        String query = "SELECT CourseName FROM Course;";
        
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
    
            while (rs.next()) {
                courseNamesList.add(rs.getString("CourseName"));
            }
    
        } catch (Exception e) {
            e.printStackTrace();
        }
    
        return courseNamesList;
    }


    public static void insertCourse(String courseName, int courseNumber, String subject, String introductionText, Difficulty difficulty, String selectedModule) {
        System.out.println("Insert Course Called");

        try {
            Connection conn = SQLServerDatabase.getDatabase().getConnection();
            String query = "INSERT INTO Course VALUES (?, ?, ?, ?, ?)";
            String updateQuery = "UPDATE Module SET CourseName = ? WHERE ModuleTitle = ?";
            
            PreparedStatement stm = conn.prepareStatement(query);

            stm.setString(1, courseName);
            stm.setInt(2, courseNumber);
            stm.setString(3, subject);
            stm.setString(4, introductionText);
            stm.setString(5, difficulty.name());

            stm.execute();

            PreparedStatement updateStm = conn.prepareStatement(updateQuery);
            updateStm.setString(1, courseName);
            updateStm.setString(2, selectedModule);

            updateStm.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    

    public static void deleteCourse(String courseName) {
        System.out.println("Delete Course Called");

        try {
            Connection conn = SQLServerDatabase.getDatabase().getConnection();

            String deleteCourseQuery = "DELETE FROM Course WHERE CourseName = ?";
            PreparedStatement deleteCourseStm = conn.prepareStatement(deleteCourseQuery);
            deleteCourseStm.setString(1, courseName);
            deleteCourseStm.execute();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void updateCourse(String courseName, int courseNumber, String subject, String introductionText, Difficulty difficulty, String selectedModule) {
        System.out.println("Update Course Called");
        try {
            Connection conn = SQLServerDatabase.getDatabase().getConnection();
            
            String updateCourseQuery = "UPDATE COURSE SET CourseName = ?, CourseNumber= ?, Subject= ?, IntroductionText= ?, Difficulty= ? WHERE CourseName= ?";
            try (PreparedStatement updateCourseStm = conn.prepareStatement(updateCourseQuery)) {
                updateCourseStm.setString(1, courseName);
                updateCourseStm.setInt(2, courseNumber);
                updateCourseStm.setString(3, subject);
                updateCourseStm.setString(4, introductionText);
                updateCourseStm.setString(5, difficulty.name());
                updateCourseStm.setString(6, courseName);
                updateCourseStm.executeUpdate();
            }
            
            String updateModuleQuery = "UPDATE Module SET CourseName = ? WHERE ModuleTitle = ?";
            try (PreparedStatement updateModuleStm = conn.prepareStatement(updateModuleQuery)) {
                updateModuleStm.setString(1, courseName);
                updateModuleStm.setString(2, selectedModule);
                updateModuleStm.executeUpdate();
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    

    public static List<String> getModuleNames() {
        System.out.println("Get ModuleNames Called");

        List<String> moduleNames = new ArrayList<>();
        Connection conn = SQLServerDatabase.getDatabase().getConnection();
        String query = "SELECT ModuleTitle FROM Module WHERE CourseName IS NULL";

        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    moduleNames.add(rs.getString("ModuleTitle"));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return moduleNames;
    }

    // CourseDetails GetModules by the coursename
    public static ObservableList<Module> getModulesForCourse(String courseName) {
        System.out.println("Get Modules of a course called");

        ObservableList<Module> modules = FXCollections.observableArrayList();

        try  {
            Connection conn = SQLServerDatabase.getDatabase().getConnection();
            PreparedStatement statement = conn.prepareStatement("SELECT * FROM Module WHERE courseName = ?");

            statement.setString(1, courseName);
            ResultSet result = statement.executeQuery();

            while (result.next()) {
                String moduleTitle = result.getString("ModuleTitle");
                String version = result.getString("ModuleVersion");
                String moduleDescription = result.getString("ModuleDescription");
                String contactPersonName = result.getString("ContactPersonName");
                String contactPersonEmail = result.getString("ContactPersonEmail");

                modules.add(new Module(moduleTitle, version, moduleDescription, contactPersonName, contactPersonEmail, courseName));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return modules;
    }

    // CourseDetails Fetch the percentage of courses finished
    public static int getStudentsCompletedForCourse(String courseName) {
        System.out.println("Number of participants Course completed Called");

        int studentsCompleted = 0;

        try {
            Connection db = SQLServerDatabase.getDatabase().getConnection();
                
            String query = "SELECT COUNT(DISTINCT Registration.EmailAddress) AS AantalCursisten " +
            "FROM Registration " +
            "WHERE Registration.CourseName = ?";

            PreparedStatement statement = db.prepareStatement(query);
            statement.setString(1, courseName);

            ResultSet result = statement.executeQuery();

            while (result.next()) {
                studentsCompleted = result.getInt("AantalCursisten");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return studentsCompleted;
    }

    // Coursedetails the average progress in percentage of a Module
    public static double getAverageProgressPercentageForModule(String moduleTitle) {
        System.out.println("Average Percentage Module Called");
    
        double averageProgressPercentage = 0.0;
    
        try {
            Connection conn = SQLServerDatabase.getDatabase().getConnection();
            String query = "SELECT AVG(asen.ViewPercentage) AS AverageProgressPercentage " +
                           "FROM CodeAcademyOffice.dbo.AmountSeen asen " +
                           "JOIN CodeAcademyOffice.dbo.ContentItem ci ON asen.ContentItemID = ci.ContentItemID " +
                           "WHERE ci.ModuleTitle = ?";
            PreparedStatement st = conn.prepareStatement(query);
            st.setString(1, moduleTitle);
            ResultSet rs = st.executeQuery();
    
            while (rs.next()) {
                averageProgressPercentage = rs.getDouble("AverageProgressPercentage");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return averageProgressPercentage;
    }

    public static void addModuletoCourse(String courseName, String selectedModule) {
        System.out.println("Add Module to a Course Called");
        try {
            Connection conn = SQLServerDatabase.getDatabase().getConnection();
            
            String updateModuleQuery = "UPDATE Module SET CourseName = ? WHERE ModuleTitle = ?";
            try (PreparedStatement updateModuleStm = conn.prepareStatement(updateModuleQuery)) {
                updateModuleStm.setString(1, courseName);
                updateModuleStm.setString(2, selectedModule);
                updateModuleStm.executeUpdate();
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void removeModuleFromCourse(String selectedModule) {
        System.out.println("Remove Module from Course Called");
        try {
            Connection conn = SQLServerDatabase.getDatabase().getConnection();
    
            String updateModuleQuery = "UPDATE Module SET CourseName = NULL WHERE ModuleTitle = ?";
            try (PreparedStatement updateModuleStm = conn.prepareStatement(updateModuleQuery)) {
                updateModuleStm.setString(1, selectedModule);
                updateModuleStm.executeUpdate();
            }
    
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
