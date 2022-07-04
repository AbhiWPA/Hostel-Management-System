package lk.ijse.d24hostel.controller;

import javafx.animation.*;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;
import lk.ijse.d24hostel.bo.custom.StudentBO;
import lk.ijse.d24hostel.bo.custom.impl.StudentBOImpl;
import lk.ijse.d24hostel.entity.Student;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;

public class StudentFormController implements Initializable {
    public AnchorPane context1;
    public ImageView imgHome;
    public ImageView imgList;
    public ImageView imgAdd;
    public ImageView imgUpdate;
    public ImageView imgDelete;
    public Label lblNote;
    public AnchorPane context2;
    public TableView tblStudent;
    public TableColumn colStId;
    public TableColumn colName;
    public TableColumn colAddress;
    public TableColumn colDob;
    public TableColumn colContact;
    public TableColumn colGender;
    public Label lblDate;
    public Label lblTime;
    StudentBO studentBO = new StudentBOImpl();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setDateAndTime();
        
        setStudentDetails();

        colStId.setCellValueFactory(new PropertyValueFactory<>("studentId"));
        colName.setCellValueFactory(new PropertyValueFactory<>("studentName"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("studentAddress"));
        colContact.setCellValueFactory(new PropertyValueFactory<>("contact"));
        colDob.setCellValueFactory(new PropertyValueFactory<>("dob"));
        colGender.setCellValueFactory(new PropertyValueFactory<>("gender"));
    }

    private void setStudentDetails() {
        tblStudent.getItems().clear();
        try {
            ArrayList<Student> allStudent = studentBO.getAllStudent();

            for (Student student : allStudent) {
                tblStudent.getItems().add(new Student(student.getStudentId(), student.getStudentName(), student.getStudentAddress(), student.getContact(), student.getDob(), student.getGender()));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void setDateAndTime() {
        lblDate.setText(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));

        Timeline clock = new Timeline(new KeyFrame(javafx.util.Duration.ZERO, e->{
            LocalTime currentTime = LocalTime.now();
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("hh:mm a");
            String time = currentTime.format(dateTimeFormatter);
            lblTime.setText(time);
        }),
                new KeyFrame(Duration.seconds(1))
        );
        clock.setCycleCount(Animation.INDEFINITE);
        clock.play();
    }

    public void navigate(MouseEvent event) throws IOException {
        if (event.getSource() instanceof ImageView) {
            ImageView icon = (ImageView) event.getSource();

            Parent root = null;

            switch (icon.getId()) {
                case "imgList":
                    loadUi("StudentListForm");
                    break;
                case "imgAdd":
                    loadUi("AddNewStudentForm");
                    break;
                case "imgUpdate":
                    loadUi("UpdateStudentDetailsForm");
                    break;
                case "imgDelete":
                    loadUi("DeleteStudentForm");
                    break;
                case "imgHome":
                    Stage stage = (Stage) imgHome.getScene().getWindow();
                    stage.close();

                    Parent parent = FXMLLoader.load(getClass().getResource("/lk/ijse/d24hostel/view/DashBoardForm.fxml"));
                    Stage stage2 = new Stage();
                    stage2.setScene(new Scene(parent));
                    stage2.setResizable(false);
                    stage2.show();
                    break;
            }

            if (root != null) {
                Scene subScene = new Scene(root);
                Stage primaryStage = (Stage) this.context1.getScene().getWindow();
                primaryStage.setScene(subScene);
                primaryStage.centerOnScreen();

                TranslateTransition tt = new TranslateTransition(Duration.millis(350), subScene.getRoot());
                tt.setFromX(-subScene.getWidth());
                tt.setToX(0);
                tt.play();

            }
        }
    }

    public void playMouseEnteredAnimation(MouseEvent event) {
        if (event.getSource() instanceof ImageView) {
            ImageView icon = (ImageView) event.getSource();

            switch (icon.getId()) {
                case "imgList":
                    lblNote.setText("Show Available Student List");
                    break;
                case "imgAdd":
                    lblNote.setText("Add New Student");
                    break;
                case "imgUpdate":
                    lblNote.setText("Update Student Details");
                    break;
                case "imgDelete":
                    lblNote.setText("Delete a Student");
                    break;
                case "imgHome":
                    lblNote.setText("Select One of above");
                    break;
            }

            ScaleTransition scaleT = new ScaleTransition(Duration.millis(200), icon);
            scaleT.setToX(1.2);
            scaleT.setToY(1.2);
            scaleT.play();

            DropShadow glow = new DropShadow();
            glow.setColor(Color.CORNFLOWERBLUE);
            glow.setWidth(20);
            glow.setHeight(20);
            glow.setRadius(20);
            icon.setEffect(glow);
        }
    }

    public void playMouseExitedAnimation(MouseEvent event) {
        if (event.getSource() instanceof ImageView) {
            ImageView icon = (ImageView) event.getSource();
            ScaleTransition scaleT = new ScaleTransition(Duration.millis(200), icon);
            scaleT.setToX(1);
            scaleT.setToY(1);
            scaleT.play();

            icon.setEffect(null);
            lblNote.setText("Select One of above");
        }
    }

    public void loadUi(String location) throws IOException {
        context2.getChildren().clear();
        Parent parent = FXMLLoader.load(getClass().getResource("../view/"+location+".fxml"));
        context2.getChildren().add(parent);
    }


}
