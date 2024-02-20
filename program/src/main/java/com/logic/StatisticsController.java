package com.logic;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import com.datastorage.StatisticsDAO;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class StatisticsController implements Initializable {

    @FXML
    private Label lblStatistics;

    @FXML
    private Label lblTopWebcasts;

    @FXML
    private Button btnBack;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Load and display Statistics 1
        showStatistics(null);

        // Load and display Statistics 5
        showTopWebcasts(null);
    }

    @FXML
    void showStatistics(ActionEvent event) {
        ObservableList<String> genderStatistics = StatisticsDAO.getGenderStatistics();

        if (!genderStatistics.isEmpty()) {
            lblStatistics.setText(String.join("\n", genderStatistics));
        } else {
            lblStatistics.setText("No data available");
        }
    }

    @FXML
    void showTopWebcasts(ActionEvent event) {
        ObservableList<String> topWebcasts = StatisticsDAO.getTopWebcasts();

        if (!topWebcasts.isEmpty()) {
            lblTopWebcasts.setText(String.join("\n", topWebcasts));
        } else {
            lblTopWebcasts.setText("No data available");
        }
    }

    @FXML
    void backToHome(ActionEvent event) {
        try {
            Stage stage = (Stage) btnBack.getScene().getWindow();

            Parent root = FXMLLoader.load(getClass().getResource("../presentation/GUI/LayoutGUI.fxml"));

            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

        } catch (IOException e) {
            
            e.printStackTrace();
        }
    }
}