package com.logic;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import com.datastorage.ParticipantDAO;
import com.datastorage.ParticipantProgressDAO;
import com.domain.Certificate;
import com.domain.Participant;
import com.domain.ParticipantProgress;
import com.domain.Registration;
import com.logic.Validation.InputValidation;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;


public class DetailParticipantController implements Initializable {

    @FXML
    private Button btnBack;

    @FXML
    private Button btnCompleteCourse;

    @FXML
    private Text efEmail;
    
    @FXML
    private Text efBirthday;
    
    @FXML
    private Text efGender;

    @FXML
    private Text efAddress;

    @FXML
    private Text efPostalCode;

    @FXML
    private Text efCity;

    @FXML
    private Text efCountry;
    
    @FXML
    private TableView<Certificate> tvCertificates;

    @FXML
    private TableColumn<Certificate, String> colCourses;

    @FXML
    private TableColumn<Certificate, String> colCerDate;

    @FXML
    private TableView<Registration> tvRegistrations;

    @FXML
    private TableColumn<Registration, String> colRegCourses;

    @FXML
    private TableColumn<Registration, String> colRegDate;

    @FXML
    private TableView<ParticipantProgress> tvProgress;

    @FXML
    private TableColumn<ParticipantProgress, String> colProCourses;

    @FXML
    private TableColumn<ParticipantProgress, String> colProMTitle;

    @FXML
    private TableColumn<ParticipantProgress, String> colProMVersion;

    @FXML
    private TableColumn<ParticipantProgress, String> colProWTitle;

    @FXML
    private TableColumn<ParticipantProgress, Double> colProPercentage;

    private String participantEmail;

    @FXML
    void handleButtonAction(ActionEvent event) throws IOException {
        if (event.getSource() == btnCompleteCourse) {
            CompleteCourse();   
        }   
    }

    public void initialize(URL url, ResourceBundle rb) {
        setParticipantEmail(participantEmail);
        loadParticipantDetails();
    }

    public void setParticipantEmail(String email) {
        this.participantEmail = email;
        efEmail.setText(email);
    }

    public void loadParticipantDetails() {
        System.out.println("Load Participant Details is called");

        ObservableList<Participant> details = ParticipantDAO.getParticipantByEmail(participantEmail);

        if (!details.isEmpty()) {
            Participant participant = details.get(0);
            
            efGender.setText(participant.getGender());
            efBirthday.setText(participant.getBirthdate());
            efAddress.setText(participant.getAddress());
            efPostalCode.setText(participant.getPostalCode());
            efCity.setText(participant.getCity());
            efCountry.setText(participant.getCountry());

            initializeCertificatesTable();
            initializeRegistrationTable();
            initializeProgressTable();
        } else {
            System.err.println("Error: No participant found with the email " + participantEmail);
        }
    }

    private void initializeCertificatesTable() {
        System.out.println("Fill up Certificates table");

        colCourses.setCellValueFactory(new PropertyValueFactory<>("CourseName"));
        colCerDate.setCellValueFactory(new PropertyValueFactory<>("CertificateDate"));

        ObservableList<Certificate> certificatesList = ParticipantProgressDAO.getCertificatesForEmail(participantEmail);

        tvCertificates.setItems(certificatesList);
    }

    private void initializeRegistrationTable() {
        System.out.println("Fill up Registration table");

        colRegCourses.setCellValueFactory(new PropertyValueFactory<>("CourseName"));
        colRegDate.setCellValueFactory(new PropertyValueFactory<>("RegistrationDate"));

        ObservableList<Registration> registrationList = ParticipantProgressDAO.getRegistrationsByEmail(participantEmail);

        tvRegistrations.setItems(registrationList);
    }

    private void initializeProgressTable() {
        System.out.println("Fill up Progress table");

        colProCourses.setCellValueFactory(new PropertyValueFactory<>("CourseName"));
        colProMTitle.setCellValueFactory(new PropertyValueFactory<>("ModuleTitle"));
        colProMVersion.setCellValueFactory(new PropertyValueFactory<>("ModuleVersion"));
        colProWTitle.setCellValueFactory(new PropertyValueFactory<>("WebcastTitle"));
        colProPercentage.setCellValueFactory(new PropertyValueFactory<>("ViewPercentage"));

        ObservableList<ParticipantProgress> progressList = ParticipantProgressDAO.getParticipantProgressByEmail(participantEmail);

        tvProgress.setItems(progressList);
    }
    
    private void CompleteCourse() {
        System.out.println("Complete Course method called");

        Registration selectedRegistration = tvRegistrations.getSelectionModel().getSelectedItem();

        if (selectedRegistration != null) {
            String email = selectedRegistration.getEmail();
            String courseName = selectedRegistration.getCourseName();
            String date = selectedRegistration.getRegistrationDate();
    
            ParticipantProgressDAO.insertCertificate(email, courseName, date);
            initializeCertificatesTable();
            initializeRegistrationTable();
            initializeProgressTable();
    
            String successMessage = "Course completed! Certificate added for " + courseName;
            InputValidation.showInformation(successMessage);
        } else {
            String warningMessage = "Please select a registration to complete a course.";
            InputValidation.showError(warningMessage);
        }

    }
    
}
