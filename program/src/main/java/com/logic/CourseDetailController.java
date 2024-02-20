package com.logic;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;

import java.io.IOException;

import com.datastorage.CourseDAO;
import com.datastorage.StatisticsDAO;
import com.domain.Course;
import com.domain.Module;

public class CourseDetailController {

    @FXML
    private Label lblCourseName, lblCourseNumber, lblSubject, lblIntroductionText, lblDifficulty;
    @FXML
    private TableView<Module> tvModules;
    @FXML
    private TableColumn<Module, String> colModuleName, colModuleDescription;
    @FXML
    private Label lblModuleTitle, lblModuleVersion, lblModuleDescription, lblContactPersonName, lblContactPersonEmail,
            lblAverageProgress;
    @FXML
    private Label lblStudentsCompleted;

    @FXML
    private Label lblgenResgistStatistics;

    @FXML
    private Button btnShowGenderStatistics;

    @FXML
    private ComboBox<String> cbModuleNames;

    @FXML
    private Button btnAddModule, btnDeleteModule;

    private Course selectedCourse;

    @FXML
    void handleButtonAction(ActionEvent event) throws IOException {
        if (event.getSource() == btnAddModule) {
            addModuleToCourse();
        }
        if (event.getSource() == btnDeleteModule) {
            removeModuleFromCourse();
        }
    }

    @FXML
    void showGenderStatistics(ActionEvent event) {
        String selectedCourseName = selectedCourse.getCourseName(); // Use the selected course

        ObservableList<String> genderStatistics = StatisticsDAO.getGenderStatisticsForCourse(selectedCourseName);

        if (genderStatistics.isEmpty()) {
            lblgenResgistStatistics.setText("No available data");
        } else {
            lblgenResgistStatistics.setText(""); // Clear previous messages

            // Update the label with gender statistics
            StringBuilder genderStatsText = new StringBuilder();
            for (String stat : genderStatistics) {
                genderStatsText.append(stat).append("\n");
            }
            lblgenResgistStatistics.setText(genderStatsText.toString());
        }
    }

    public void initialize() {
        configureTableView();
        setupModuleSelection();
        cbModuleNames.setItems(FXCollections.observableArrayList(CourseDAO.getModuleNames()));
    }

    private void setupModuleSelection() {
        tvModules.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                updateModuleDetails(newSelection);
            }
        });
    }

    public void setSelectedCourse(Course course) {
        this.selectedCourse = course;
        displayCourseDetails();

        ObservableList<Module> modules = CourseDAO.getModulesForCourse(selectedCourse.getCourseName());
        tvModules.setItems(modules);

        int studentsCompleted = CourseDAO.getStudentsCompletedForCourse(selectedCourse.getCourseName());
        lblStudentsCompleted.setText(String.valueOf(studentsCompleted));
    }

    private void configureTableView() {
        System.out.println("Fill up Course Details table");

        colModuleName.setCellValueFactory(new PropertyValueFactory<>("moduleTitle"));
        colModuleDescription.setCellValueFactory(new PropertyValueFactory<>("moduleDescription"));
    }

    private void displayCourseDetails() {
        System.out.println("Fill up Course Details side");

        lblCourseName.setText(selectedCourse.getCourseName());
        lblCourseNumber.setText(String.valueOf(selectedCourse.getCourseNumber()));
        lblSubject.setText(selectedCourse.getSubject());
        lblIntroductionText.setText(selectedCourse.getIntroductionText());
        lblDifficulty.setText(selectedCourse.getDifficulty().name());
    }

    private void updateModuleDetails(Module module) {
        System.out.println("Fill up Module Details table");

        lblModuleTitle.setText(module.getModuleTitle());
        lblModuleVersion.setText(module.getVersion());
        lblModuleDescription.setText(module.getModuleDescription());
        lblContactPersonName.setText(module.getContactPersonName());
        lblContactPersonEmail.setText(module.getContactPersonEmail());

        double averageProgress = CourseDAO.getAverageProgressPercentageForModule(module.getModuleTitle());

        lblAverageProgress.setText(String.format("%.2f%%", averageProgress));
    }

    private void addModuleToCourse() {
        System.out.println("Add Module to Course called");

        String courseName = selectedCourse.getCourseName();
        String selectedModule = cbModuleNames.getSelectionModel().getSelectedItem();

        CourseDAO.addModuletoCourse(courseName, selectedModule);

        ObservableList<Module> modules = CourseDAO.getModulesForCourse(selectedCourse.getCourseName());
        cbModuleNames.setItems(FXCollections.observableArrayList(CourseDAO.getModuleNames()));
        tvModules.setItems(modules);
    }

    private void removeModuleFromCourse() {
        System.out.println("Remove Module from Course called");

        String selectedModule = tvModules.getSelectionModel().getSelectedItem().getModuleTitle();

        CourseDAO.removeModuleFromCourse(selectedModule);

        ObservableList<Module> modules = CourseDAO.getModulesForCourse(selectedCourse.getCourseName());
        cbModuleNames.setItems(FXCollections.observableArrayList(CourseDAO.getModuleNames()));
        tvModules.setItems(modules);
    }

}

// package com.logic;

// import javafx.fxml.FXML;
// import javafx.scene.control.Button;
// import javafx.scene.control.ComboBox;
// import javafx.scene.control.Label;
// import javafx.scene.control.TableColumn;
// import javafx.scene.control.TableView;
// import javafx.scene.control.cell.PropertyValueFactory;
// import javafx.collections.FXCollections;
// import javafx.collections.ObservableList;
// import javafx.event.ActionEvent;

// import java.io.IOException;

// import com.datastorage.CourseDAO;
// import com.datastorage.StatisticsDAO;
// import com.domain.Course;
// import com.domain.Module;

// public class CourseDetailController {

// @FXML
// private Label lblCourseName, lblCourseNumber, lblSubject,
// lblIntroductionText, lblDifficulty, lblStudentsCompleted;

// @FXML
// private TableView<Module> tvModules;

// @FXML
// private TableColumn<Module, String> colModuleName, colModuleDescription;

// @FXML
// private Label lblModuleTitle, lblModuleVersion, lblModuleDescription,
// lblContactPersonName, lblContactPersonEmail, lblAverageProgress;

// @FXML
// private ComboBox<String> cbModuleNames;

// @FXML
// private Button btnAddModule, btnDeleteModule;

// private Course selectedCourse;

// @FXML
// void handleButtonAction(ActionEvent event) throws IOException {
// if (event.getSource() == btnAddModule) {
// addModuleToCourse();
// } else if (event.getSource() == btnDeleteModule) {
// removeModuleFromCourse();
// }
// }

// public void initialize() {
// configureTableView();
// setupModuleSelection();
// cbModuleNames.setItems(FXCollections.observableArrayList(CourseDAO.getModuleNames()));
// }

// private void setupModuleSelection() {
// tvModules.getSelectionModel().selectedItemProperty().addListener((obs,
// oldSelection, newSelection) -> {
// if (newSelection != null) {
// updateModuleDetails(newSelection);
// }
// });
// }

// public void setSelectedCourse(Course course) {
// this.selectedCourse = course;
// displayCourseDetails();

// ObservableList<Module> modules =
// CourseDAO.getModulesForCourse(selectedCourse.getCourseName());
// tvModules.setItems(modules);

// // Update number of students completed for the selected course
// int studentsCompleted =
// CourseDAO.getStudentsCompletedForCourse(selectedCourse.getCourseName());
// lblStudentsCompleted.setText(String.valueOf(studentsCompleted));

// // Update gender statistics for the selected course
// ObservableList<String> genderStats =
// StatisticsDAO.getGenderStatisticsForCourse(selectedCourse.getCourseName());
// System.out.println("Gender Statistics for Course: " + genderStats);
// }

// private void configureTableView() {
// colModuleName.setCellValueFactory(new PropertyValueFactory<>("moduleTitle"));
// colModuleDescription.setCellValueFactory(new
// PropertyValueFactory<>("moduleDescription"));
// }

// private void displayCourseDetails() {
// lblCourseName.setText(selectedCourse.getCourseName());
// lblCourseNumber.setText(String.valueOf(selectedCourse.getCourseNumber()));
// lblSubject.setText(selectedCourse.getSubject());
// lblIntroductionText.setText(selectedCourse.getIntroductionText());
// lblDifficulty.setText(selectedCourse.getDifficulty().name());
// }

// private void updateModuleDetails(Module module) {
// lblModuleTitle.setText(module.getModuleTitle());
// lblModuleVersion.setText(module.getVersion());
// lblModuleDescription.setText(module.getModuleDescription());
// lblContactPersonName.setText(module.getContactPersonName());
// lblContactPersonEmail.setText(module.getContactPersonEmail());

// double averageProgress =
// CourseDAO.getAverageProgressPercentageForModule(module.getModuleTitle());
// lblAverageProgress.setText(String.format("%.2f%%", averageProgress));
// }

// private void addModuleToCourse() {
// String courseName = selectedCourse.getCourseName();
// String selectedModule = cbModuleNames.getSelectionModel().getSelectedItem();
// CourseDAO.addModuletoCourse(courseName, selectedModule);
// ObservableList<Module> modules =
// CourseDAO.getModulesForCourse(selectedCourse.getCourseName());
// cbModuleNames.setItems(FXCollections.observableArrayList(CourseDAO.getModuleNames()));
// tvModules.setItems(modules);
// }

// private void removeModuleFromCourse() {
// String selectedModule =
// tvModules.getSelectionModel().getSelectedItem().getModuleTitle();
// CourseDAO.removeModuleFromCourse(selectedModule);
// ObservableList<Module> modules =
// CourseDAO.getModulesForCourse(selectedCourse.getCourseName());
// cbModuleNames.setItems(FXCollections.observableArrayList(CourseDAO.getModuleNames()));
// tvModules.setItems(modules);
// }
// }
