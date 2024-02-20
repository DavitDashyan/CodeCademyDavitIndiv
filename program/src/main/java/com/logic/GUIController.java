package com.logic;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;


public class GUIController implements Initializable {
    
    @FXML
    private Button btnParticipants;

    @FXML
    private Button btnCourses;

    @FXML
    private Button btnRegistrations;

    @FXML
    private Button btnCertificate;

    @FXML
    private Button btnStatistics;
    
    @FXML
    void handleButtonAction(ActionEvent event) throws IOException {
        Stage stage = null;
        Parent root = null;

        if (event.getSource() == btnParticipants) {
            stage = (Stage) btnParticipants.getScene().getWindow();
            root = FXMLLoader.load(getClass().getResource("../presentation/Participant/layoutParticipant.fxml"));
        } 
        if (event.getSource() == btnCourses) {
            stage = (Stage) btnCourses.getScene().getWindow();
            root = FXMLLoader.load(getClass().getResource("../presentation/Course/layoutCourse.fxml"));
        }
        if (event.getSource() == btnRegistrations) {
            stage = (Stage) btnRegistrations.getScene().getWindow();
            root = FXMLLoader.load(getClass().getResource("../presentation/Registration/layoutRegistration.fxml"));
        }       
        if (event.getSource() == btnStatistics) {
            stage = (Stage) btnStatistics.getScene().getWindow();
            root = FXMLLoader.load(getClass().getResource("../presentation/Statistics/layoutStatistics.fxml"));
        }

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void initialize(URL url, ResourceBundle rb) {
    }
}
