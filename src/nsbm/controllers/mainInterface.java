package nsbm.controllers;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class mainInterface {

    @FXML
    public Text systemtime;

    @FXML
    public Label studentcount;

    @FXML
    public Label lecturecount;

    @FXML
    public JFXButton regpost;

    @FXML
    public JFXButton regunder;
    public JFXButton regnew;
    public JFXButton indexins;

    @FXML
    private AnchorPane menuuni;

    @FXML
    private AnchorPane menufaculty;

    @FXML
    private AnchorPane menustaff;

    @FXML
    private AnchorPane menuhome;

    @FXML
    private AnchorPane menustudent;

    @FXML
    private JFXButton btnhome;

    @FXML
    private JFXButton btnstudent;

    @FXML
    private JFXButton btnstaff;

    @FXML
    private JFXButton btnfaculty;

    @FXML
    private JFXButton btnuniversity;

    @FXML
    private JFXButton btnusers;

    @FXML
    private JFXButton btnlogout;

    public void initialize() throws SQLException {
        settime();
        setDashboard();
    }

    @FXML
    public void handleButtonAction(javafx.event.ActionEvent actionEvent) throws IOException {
        if(actionEvent.getSource()==btnhome){
            menuhome.toFront();
        }
        else if(actionEvent.getSource()==btnstudent){
            menustudent.toFront();
        }
        else if(actionEvent.getSource()==btnstaff){
            menustaff.toFront();
        }
        else if(actionEvent.getSource()==btnfaculty){
            menufaculty.toFront();
        }
        else if(actionEvent.getSource()==btnuniversity){
            menuuni.toFront();
        }
        else if(actionEvent.getSource()==btnusers){
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../resources/view/user/create.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setTitle("Add New User");
            stage.setScene(new Scene(root1));
            stage.show();
        }
        else if(actionEvent.getSource()==btnlogout){
            Stage thiswin = (Stage) btnlogout.getScene().getWindow();
            thiswin.close();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../resources/view/newlogin.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setTitle("Login to the System");
            stage.setScene(new Scene(root1));
            stage.show();
        }
    }

    private void settime(){
//        Thread.sleep(1000);
        Calendar cal= Calendar.getInstance();
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy/MM/dd");
        String time=sdf.format(cal.getTime());
        systemtime.setText(time);
    }
    private void setDashboard() throws SQLException {
        int students=0,lecturers=0;
        Connection con = ConnectionManager.getConnection();
        String squery="SELECT * FROM students";
        String lquery="SELECT * FROM lecturers";
        Statement sq=con.prepareStatement(squery);
        Statement lq=con.prepareStatement(lquery);
        ResultSet rlq=((PreparedStatement) lq).executeQuery();
        ResultSet rsq=((PreparedStatement) sq).executeQuery();
        while (rsq.next()){
            students++;
        }
        while (rlq.next()){
            lecturers++;
        }
        studentcount.setText(Integer.toString(students));
        lecturecount.setText(Integer.toString(lecturers));
    }

    public void regunder(javafx.event.ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../resources/view/student/create/regundergrad.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.setTitle("Undergraduate Registration");
        stage.setScene(new Scene(root1));
        stage.show();
    }

    public void regpost(javafx.event.ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../resources/view/student/create/regpostgrad.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.setTitle("Postgraduate Registration");
        stage.setScene(new Scene(root1));
        stage.show();
    }

    public void regnewstudent(javafx.event.ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../resources/view/student/studenttype.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Register new Student");
        stage.setScene(new Scene(root1));
        stage.show();
    }

    public void undergradindex(javafx.event.ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../resources/view/student/index/indexundergrad.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("All Undergraduates");
        stage.setScene(new Scene(root1));
        stage.show();
    }

    public void postgradindex(javafx.event.ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../resources/view/student/index/indexpostgrad.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("All Postgraduates");
        stage.setScene(new Scene(root1));
        stage.show();
    }

    public void findstudent(javafx.event.ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../resources/view/student/findbox.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Find Student");
        stage.setScene(new Scene(root1));
        stage.show();
    }

    public void delete(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../resources/view/student/delete/delfindstudent.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Find Student");
        stage.setScene(new Scene(root1));
        stage.show();
    }

    public void subselect(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../resources/view/student/enroll/select.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.setTitle("Subject Enroll");
        stage.setScene(new Scene(root1));
        stage.show();
    }

    public void viewlec(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../resources/view/lecturer/index.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.setTitle("View Lecturers");
        stage.setScene(new Scene(root1));
        stage.show();
    }

    public void reglec(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../resources/view/lecturer/create.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.setTitle("Register Lecturer");
        stage.setScene(new Scene(root1));
        stage.show();
    }

    public void regins(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../resources/view/instructor/create.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.setTitle("Register Instructor");
        stage.setScene(new Scene(root1));
        stage.show();
    }

    public void indexins(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../resources/view/instructor/index.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.setTitle("Register Instructor");
        stage.setScene(new Scene(root1));
        stage.show();
    }

    public void findstaff(ActionEvent actionEvent) throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../resources/view/stafffindbox.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.setTitle("Register Instructor");
        stage.setScene(new Scene(root1));
        stage.show();
    }

    public void opencourse(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../resources/view/course/index.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.setTitle("Index Courses");
        stage.setScene(new Scene(root1));
        stage.show();
    }

    public void opensubjects(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../resources/view/subject/index.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.setTitle("Index Subjects");
        stage.setScene(new Scene(root1));
        stage.show();
    }

    public void opentimetable(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../resources/view/timetable/select.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.setTitle("Index Timetable");
        stage.setScene(new Scene(root1));
        stage.show();

    }

    public void openlabs(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../resources/view/lab/index.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.setTitle("Index Labs");
        stage.setScene(new Scene(root1));
        stage.show();
    }

    public void opensem(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../resources/view/semester/index.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.setTitle("Index Semesters");
        stage.setScene(new Scene(root1));
        stage.show();
    }

    public void openassignment(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../resources/view/assignment/select.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.setTitle("Index Assignments");
        stage.setScene(new Scene(root1));
        stage.show();
    }

    public void openpayment(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../resources/view/payment/select.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.setTitle("Select Payments");
        stage.setScene(new Scene(root1));
        stage.show();
    }

    public void openexam(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../resources/view/exam/select.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.setTitle("Index Exam");
        stage.setScene(new Scene(root1));
        stage.show();
    }

    public void addmarks(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../resources/view/marks/select.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.setTitle("Add Marks");
        stage.setScene(new Scene(root1));
        stage.show();
    }

    public void genreport(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../resources/view/exam/select.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.setTitle("Generate Reports");
        stage.setScene(new Scene(root1));
        stage.show();
    }

    public void paymentstd(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../resources/view/payment/select.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.setTitle("Select Student Payments");
        stage.setScene(new Scene(root1));
        stage.show();
    }
}
