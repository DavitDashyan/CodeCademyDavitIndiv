package com.presentation.CourseDetail;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class CourseDetailUI extends Application {

    @Override
    public void start(Stage stage) throws Exception {

        Parent root = FXMLLoader.load(getClass().getResource("../CourseDetails/layoutCourseDetails.fxml"));

        Scene scene = new Scene(root);

        stage.setScene(scene);

        stage.setTitle("Emma van den Broek (2200567), Ivan van Dijk (2196154), Davit Dashyan (2206322)");

        stage.show();
    }
}
