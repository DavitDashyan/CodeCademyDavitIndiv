package com.logic;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ResourceBundle;
import com.datastorage.ParticipantDAO;
import com.domain.Participant;
import com.logic.Validation.InputValidation;

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
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class ParticipantController implements Initializable {

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
    private Button btnDetails;

    @FXML
    private TableColumn<Participant, String> colDate;

    @FXML
    private TableColumn<Participant, Integer> colID;

    @FXML
    private TableColumn<Participant, String> colPostalCode;

    @FXML
    private TableColumn<Participant, String> colEmail;

    @FXML
    private TableColumn<Participant, String> colName;

    @FXML
    private TableColumn<Participant, String> colGender;

    @FXML
    private TableColumn<Participant, String> colAddress;

    @FXML
    private TableColumn<Participant, String> colCity;

    @FXML
    private TableColumn<Participant, String> colCountry;

    @FXML
    private TextField tfDateYear;

    @FXML
    private TextField tfDateMonth;

    @FXML
    private TextField tfDateDay;

    @FXML
    private TextField tfAddress;

    @FXML
    private TextField tfCity;

    @FXML
    private TextField tfCountry;

    @FXML
    private TextField tfEmail;

    @FXML
    private TextField tfGender;

    @FXML
    private TextField tfName;

    @FXML
    private TextField tfPostalCode;

    @FXML
    private TableView<Participant> tvParticipants;

    boolean isClicked = false;

    ObservableList<Participant> participants;

    @FXML
    void handleButtonAction(ActionEvent event) throws IOException {
        if (event.getSource() == btnInsert) {
            if (validateInput()) {
             insertParticipant();
            }
        }
        else if (event.getSource() == btnDelete) {
            deleteParticipant();
        } 
        else if (event.getSource() == btnClear) {
            isClicked = true;
            clear();    
        } 
        else if (event.getSource() == btnUpdate && isClicked) {
            if (validateInput()) {
                updateParticipant();
                isClicked = false;
                tfEmail.setDisable(false);
            }
        }
        else if (event.getSource() == btnUpdate && !isClicked) {
            isClicked = true;
            setText();
            tfEmail.setDisable(true);
        }
        else if(event.getSource() == btnBack) {
            backToHome();
        }
        else if (event.getSource() == btnDetails) {
            toParticipantDetails();
        }
    }

    public void initialize(URL url, ResourceBundle rb) {
        showParticipant();
    }

    public void showParticipant() {
        System.out.println("Show Participants method called");

        ObservableList<Participant> participantList = ParticipantDAO.getParticipants();
        
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        
        tvParticipants.setItems(participantList);
    }

    private void insertParticipant() {
        System.out.println("Insert Participant method called");

        LocalDate birthdate = LocalDate.of(
            Integer.parseInt(tfDateYear.getText()),
            Integer.parseInt(tfDateMonth.getText()),
            Integer.parseInt(tfDateDay.getText())
        );

        ParticipantDAO.insertParticipant(
            tfEmail.getText(),
            tfName.getText(),
            birthdate,
            tfGender.getText(),
            tfAddress.getText(),
            tfPostalCode.getText(),
            tfCity.getText(),
            tfCountry.getText()
        );

        clear();
        showParticipant();
    }
    
    public void deleteParticipant() {
        System.out.println("Delete Participant method called");

        String selectedEmail = tvParticipants.getSelectionModel().getSelectedItem().getEmail();

        ParticipantDAO.deleteParticipant(selectedEmail);

        showParticipant();
    }

    public void updateParticipant() {
        System.out.println("Update Participant method called");

        String email = tfEmail.getText();
        String name = tfName.getText();
        Date birthdate = Date.valueOf(tfDateYear.getText() + "-" + tfDateMonth.getText() + "-" + tfDateDay.getText());
        String gender = tfGender.getText();
        String address = tfAddress.getText();
        String postalCode = tfPostalCode.getText();
        String city = tfCity.getText();
        String country = tfCountry.getText();

        ParticipantDAO.updateParticipant(email, name, birthdate, gender, address, postalCode, city, country);

        clear();
        showParticipant();
    }

    public void setText() {
        System.out.println("Set Text Participant");

        Participant selectedParticipant = tvParticipants.getSelectionModel().getSelectedItem();

        tfEmail.setText(tvParticipants.getSelectionModel().getSelectedItem().getEmail());
        tfName.setText(tvParticipants.getSelectionModel().getSelectedItem().getName());

        tfDateYear.setText(tvParticipants.getSelectionModel().getSelectedItem().getBirthdate());
        tfDateMonth.setText(tvParticipants.getSelectionModel().getSelectedItem().getBirthdate());
        tfDateDay.setText(tvParticipants.getSelectionModel().getSelectedItem().getBirthdate());

        String[] dateParts = selectedParticipant.getBirthdate().split("-");
            if (dateParts.length == 3) {
                tfDateYear.setText(dateParts[0]);
                tfDateMonth.setText(dateParts[1]);
                tfDateDay.setText(dateParts[2]);
            }

        tfGender.setText(tvParticipants.getSelectionModel().getSelectedItem().getGender());
        tfAddress.setText(tvParticipants.getSelectionModel().getSelectedItem().getAddress());
        tfPostalCode.setText(tvParticipants.getSelectionModel().getSelectedItem().getPostalCode());
        tfCity.setText(tvParticipants.getSelectionModel().getSelectedItem().getCity());
        tfCountry.setText(tvParticipants.getSelectionModel().getSelectedItem().getCountry());
    }

    public void clear() {
        System.out.println("Clear");
        
        tfEmail.clear();
        tfName.clear();
        tfDateYear.clear();
        tfDateMonth.clear();
        tfDateDay.clear();
        tfGender.clear();
        tfAddress.clear();
        tfPostalCode.clear();
        tfCity.clear();
        tfCountry.clear();
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

    public void toParticipantDetails() throws IOException{
        System.out.println("To Participant Details");

        FXMLLoader loader = new FXMLLoader(getClass().getResource("../presentation/DetailsParticipant/layoutDetailParticipant.fxml"));
        Parent root = loader.load();
        DetailParticipantController detailController = loader.getController();
    
        String selectedEmail = tvParticipants.getSelectionModel().getSelectedItem().getEmail();
        System.out.println(selectedEmail);
    
        detailController.setParticipantEmail(selectedEmail);

        detailController.loadParticipantDetails();
    
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    }

    private boolean validateInput() {
        // Check if email is right
        if (!InputValidation.isValidEmail(tfEmail.getText())) {
            InputValidation.showError("Invalid email format. \nPlease enter a valid email address.");
                return false;
        }

        // Checking if postalcode is right NNNN<space>MM
        if (!InputValidation.isValidDutchPostalCode(tfPostalCode.getText())) {
            InputValidation.showError("Invalid Dutch postal code. Please enter a valid postal code. \nIt needs to have 4 numbers, a space and two uppercase letters.");
                return false;
        }

        // Check if the date is correct
        int day = Integer.parseInt(tfDateDay.getText());
        int month = Integer.parseInt(tfDateMonth.getText());
        int year = Integer.parseInt(tfDateYear.getText());
        
        if (!InputValidation.isValidDate(day, month, year)) {
            InputValidation.showError("Invalid date. \nPlease enter a valid date.");
                return false;
        }

        return true;
    }
   
}
