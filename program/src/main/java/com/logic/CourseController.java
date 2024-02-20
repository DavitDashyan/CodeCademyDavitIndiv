package com.logic;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import com.datastorage.CourseDAO;
import com.domain.Course;
import com.domain.Difficulty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.scene.control.ComboBox;

public class CourseController implements Initializable {

    @FXML
    private Button btnBack;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnInsert;

    @FXML
    private Button btnUpdate;

    @FXML
    private Button btnClear;

    @FXML
    private Button btnViewCourse;

    @FXML
    private TableColumn<Course, String> colCourseName;

    @FXML
    private TableColumn<Course, Integer> colCourseNumber;

    @FXML
    private TextField tfCoursename;

    @FXML
    private TextField tfCoursenumber;

    @FXML
    private TextField tfSubject;

    @FXML
    private TextArea tfIntroductiontext;

    @FXML
    private ComboBox<String> cbDifficulty;

    @FXML
    private TableView<Course> tvCourses;

    @FXML
    private ComboBox<String> cbModuleNames;

    boolean isClicked = false;

    ObservableList<Course> courses;

    @FXML
    void handleButtonAction(ActionEvent event) throws IOException {
        if (event.getSource() == btnInsert) {
            insertCourse();
        } 
        if (event.getSource() == btnDelete) {
            deleteCourse();
        } 
        if (event.getSource() == btnClear) {
            isClicked = true;
            clear();    
        } 
        if(event.getSource() == btnBack) {
            backToHome();
        }
        if (event.getSource() == btnUpdate && !isClicked) {
            isClicked = true;
            setText();
            cbModuleNames.setDisable(true);
            tfCoursename.setDisable(true);
        } else if (event.getSource() == btnUpdate && isClicked) {
            updateCourse();
            isClicked = false;
            cbModuleNames.setDisable(false);
            tfCoursename.setDisable(false);
        }
        if (event.getSource() == btnViewCourse) {
            toCourseDetails();
        }
    }

    public void initialize(URL url, ResourceBundle rb) {
        showCourse();
        cbModuleNames.setItems(FXCollections.observableArrayList(CourseDAO.getModuleNames()));
            cbDifficulty.setItems(FXCollections.observableArrayList(
            Difficulty.Beginner.name(),
            Difficulty.Intermediate.name(),
            Difficulty.Expert.name()
    ));
    }

    public void showCourse() {
        System.out.println("Show Course method called");

        ObservableList<Course> courseList = CourseDAO.getCourse();

        colCourseName.setCellValueFactory(new PropertyValueFactory<>("CourseName"));
        colCourseNumber.setCellValueFactory(new PropertyValueFactory<>("courseNumber"));

        tvCourses.setItems(courseList);
    }
    
    private void insertCourse() {
        System.out.println("Insert Course method called");
    
        Difficulty difficulty = Difficulty.valueOf(cbDifficulty.getSelectionModel().getSelectedItem());
        String selectedModule = cbModuleNames.getSelectionModel().getSelectedItem();
    
        CourseDAO.insertCourse(
            tfCoursename.getText(),
            Integer.parseInt(tfCoursenumber.getText()),
            tfSubject.getText(),
            tfIntroductiontext.getText(),
            difficulty,
            selectedModule
        );
    
        clear();
        showCourse();
    }
    
    public void deleteCourse() {
        System.out.println("Delete Course method called");

        String selectedCourseName = tvCourses.getSelectionModel().getSelectedItem().getCourseName();            
            
        CourseDAO.deleteCourse(selectedCourseName);

        showCourse();
    }

    public void updateCourse() {
        System.out.println("Update Course method called");
    
        String courseName = tfCoursename.getText();
        int courseNumber = Integer.parseInt(tfCoursenumber.getText());
        String subject = tfSubject.getText();
        String introductionText = tfIntroductiontext.getText();
        Difficulty difficulty = Difficulty.valueOf(cbDifficulty.getSelectionModel().getSelectedItem());
        String selectedModule = cbModuleNames.getSelectionModel().getSelectedItem();
    
        CourseDAO.updateCourse(courseName, courseNumber, subject, introductionText, difficulty, selectedModule);
    
        clear();
        showCourse();
    }
    
    public void setText() {
        System.out.println("Set Text Course");

        Course selectedCourse = tvCourses.getSelectionModel().getSelectedItem();

        tfCoursename.setText(tvCourses.getSelectionModel().getSelectedItem().getCourseName());
        tfCoursenumber.setText(Integer.toString(selectedCourse.getCourseNumber()));
        tfSubject.setText(tvCourses.getSelectionModel().getSelectedItem().getSubject());
        tfIntroductiontext.setText(tvCourses.getSelectionModel().getSelectedItem().getIntroductionText());
        cbDifficulty.getSelectionModel().select(selectedCourse.getDifficulty().toString());
    }

    public void clear() {
        System.out.println("Clear");

        tfCoursename.clear();
        tfCoursenumber.clear();
        tfSubject.clear();
        tfIntroductiontext.clear();
        cbDifficulty.setValue(null);
        cbModuleNames.setValue(null);
    }

    public void backToHome() throws IOException{
        System.out.println("Back To Home");

        Stage stage = null;
        Parent root = null;

        stage = (Stage) btnBack.getScene().getWindow();
        root = FXMLLoader.load(getClass().getResource("../presentation/GUI/LayoutGUI.fxml"));

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }


    private void toCourseDetails() throws IOException {
        System.out.println("To Course Details");

        Course selectedCourse = tvCourses.getSelectionModel().getSelectedItem();

        if (selectedCourse == null) {
            return;
        }

        FXMLLoader loader = new FXMLLoader(getClass().getResource("../presentation/CourseDetail/layoutCourseDetail.fxml"));
        Parent courseDetailsRoot = loader.load();

        CourseDetailController courseDetailController = loader.getController();
        courseDetailController.setSelectedCourse(selectedCourse);

        Stage stage = new Stage();

        stage.setScene(new Scene(courseDetailsRoot));

        stage.show();
    }
}

