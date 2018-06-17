package nsbm.controllers;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import nsbm.models.semester;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class editSemester {

    @FXML
    private JFXTextField id;

    @FXML
    private JFXComboBox faculty;

    @FXML
    private JFXComboBox semester;

    @FXML
    private JFXComboBox year;

    @FXML
    private JFXDatePicker start;

    @FXML
    private JFXDatePicker end;

    public void initialize() throws SQLException {
        setfaculty();
        setYear();
        setSemester();
        setData();
    }

    @FXML
    void cancel(ActionEvent event) {
        Stage thiswin=(Stage) faculty.getScene().getWindow();
        thiswin.close();
    }

    @FXML
    void update(ActionEvent event) throws SQLException, IOException {
        nsbm.models.semester sem=new semester(id.getText(),year.getSelectionModel().getSelectedItem().toString(),semester.getSelectionModel().getSelectedItem().toString(),start.getValue().toString(),end.getValue().toString(),faculty.getSelectionModel().getSelectedItem().toString());
        boolean result=sem.update();
        if(result){
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../resources/view/alertbox/updateSuccess.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Semester Registration");
            stage.setScene(new Scene(root1));
            stage.showAndWait();
            Stage thiswin=(Stage) faculty.getScene().getWindow();
            thiswin.close();
        }else{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../resources/view/alertbox/updateFailed.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Semester Registration");
            stage.setScene(new Scene(root1));
            stage.showAndWait();
            Stage thiswin=(Stage) faculty.getScene().getWindow();
            thiswin.close();
        }
    }
    private void setfaculty() throws SQLException {
        ArrayList<String> all= nsbm.models.faculty.getall();
        for(String code:all){
            faculty.getItems().add(code);
        }
    }

    private void setYear(){
        year.getItems().add("2015");
        year.getItems().add("2016");
        year.getItems().add("2017");
        year.getItems().add("2018");
        year.getItems().add("2019");
        year.getItems().add("2020");
        year.getItems().add("2021");
        year.getItems().add("2022");
        year.getItems().add("2023");
        year.getItems().add("2024");
        year.getItems().add("2025");
        year.getItems().add("2026");
        year.getItems().add("2027");
        year.getItems().add("2028");
        year.getItems().add("2029");
        year.getItems().add("2030");
    }

    private void setSemester(){
        semester.getItems().add("First");
        semester.getItems().add("Second");
    }

    private void setData() throws SQLException{
        DateTimeFormatter fomatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        String semid=indexSemester.getSelecter();
        nsbm.models.semester sem= nsbm.models.semester.findsemester(semid);
        id.setText(sem.getSemester_id());
        faculty.getSelectionModel().select(sem.getFaculty());
        semester.getSelectionModel().select(sem.getSemester_number());
        year.getSelectionModel().select(sem.getYear());
        start.setValue(LocalDate.parse(sem.getStart_date(),fomatter));
        end.setValue(LocalDate.parse(sem.getEnd_date(),fomatter));

    }
}
