package com.presentation.Registration;

import javafx.application.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class RegistrationUI extends Application {

  @Override
    public void start(Stage stage) throws Exception{

        Parent root = FXMLLoader.load(getClass().getResource("./layoutRegistration.fxml"));

        Scene scene = new Scene(root);

        stage.setScene(scene);

        stage.setTitle("Davit Dashyan (2206322)");

        stage.show();
    }
}
