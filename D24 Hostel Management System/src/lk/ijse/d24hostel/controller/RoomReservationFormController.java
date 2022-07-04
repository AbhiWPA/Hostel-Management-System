package lk.ijse.d24hostel.controller;

import com.jfoenix.controls.*;
import javafx.animation.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;
import lk.ijse.d24hostel.bo.custom.ReservationBO;
import lk.ijse.d24hostel.bo.custom.RoomsBO;
import lk.ijse.d24hostel.bo.custom.impl.ReservationBOImpl;
import lk.ijse.d24hostel.bo.custom.impl.RoomBOImpl;
import lk.ijse.d24hostel.dao.custom.RoomsDAO;
import lk.ijse.d24hostel.entity.Reservation;
import lk.ijse.d24hostel.entity.Rooms;
import lk.ijse.d24hostel.entity.Student;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;

public class RoomReservationFormController implements Initializable {
    public ImageView imgHome;
    public AnchorPane context;
    public JFXTextField txtStudentName;
    public JFXTextField txtStudentAddress;
    public JFXTextField txtContact;
    public JFXTextField txtStudentId;
    public JFXRadioButton rdoMale;
    public JFXRadioButton rdoFemale;
    public JFXDatePicker dateDoB;
    public JFXComboBox cmbStudentId;
    public JFXButton btnCheckRooms;
    public JFXComboBox cmbRoomId;
    public JFXTextField txtRoomType;
    public JFXTextField txtKeyMoney;
    public JFXRadioButton rdoPayNow;
    public JFXRadioButton rdoPayLater;
    public JFXButton btnConfirm;
    public Label lblDate;
    public Label lblTime;
    public JFXButton btnAddStudent;
    String gender;
    String payment;
    ReservationBO reservationBO = new ReservationBOImpl();
    RoomsBO roomsBO = new RoomBOImpl();
    NotificationController n = new NotificationController();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setDateAndTime();
        setStudentDetails();
        setRoomDetails();
    }

    private void setRoomDetails() {
        try {

            ArrayList<String> ids = reservationBO.getRoomIds();

            cmbRoomId.getItems().addAll(ids);


            cmbRoomId.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
                String code= (String) newValue;


                ArrayList<Rooms> rooms = null;

                try {
                    rooms = reservationBO.getAllRoomsById(code);
                    for (Rooms room : rooms) {
                        txtRoomType.setText(room.getRoomType());
                        txtKeyMoney.setText(String.valueOf(room.getKeyMoney()));
                    }
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            });
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void setStudentDetails() {
        try {

            ArrayList<String> ids = reservationBO.getStudentIds();

            cmbStudentId.getItems().addAll(ids);


            cmbStudentId.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
                String code= (String) newValue;


                ArrayList<Student> students = null;

                try {
                    students = reservationBO.getAllStudentsById(code);
                    for (Student student : students) {
                        txtStudentId.setText(student.getStudentId());
                        txtStudentName.setText(student.getStudentName());
                        txtStudentAddress.setText(student.getStudentAddress());
                        txtContact.setText(String.valueOf(student.getContact()));
                        dateDoB.setValue(LocalDate.parse(student.getDob()));
                        if (student.getGender().equals("Male")){
                            rdoMale.setSelected(true);
                        }else if (student.getGender().equals("Female")){
                            rdoFemale.setSelected(true);
                        }
                    }
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            });
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void setDateAndTime() {
        lblDate.setText(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));

        Timeline clock = new Timeline(new KeyFrame(javafx.util.Duration.ZERO, e->{
            LocalTime currentTime = LocalTime.now();
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("hh:mm:ss a");
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
                Stage primaryStage = (Stage) this.context.getScene().getWindow();
                primaryStage.setScene(subScene);
                primaryStage.centerOnScreen();

                TranslateTransition tt = new TranslateTransition(Duration.millis(350), subScene.getRoot());
                tt.setFromX(-subScene.getWidth());
                tt.setToX(0);
                tt.play();

            }
        }
    }

    public void playMouseEnterAnimation(MouseEvent event) {
        if (event.getSource() instanceof ImageView) {
            ImageView icon = (ImageView) event.getSource();

            switch (icon.getId()) {
                case "imgHome":
                    break;
            }

            ScaleTransition scaleT = new ScaleTransition(Duration.millis(200), icon);
            scaleT.setToX(1.2);
            scaleT.setToY(1.2);
            scaleT.play();

            DropShadow glow = new DropShadow();
            glow.setColor(Color.DARKSALMON);
            glow.setWidth(20);
            glow.setHeight(20);
            glow.setRadius(20);
            icon.setEffect(glow);
        }
    }

    public void playMouseExitAnimation(MouseEvent event) {
        if (event.getSource() instanceof ImageView) {
            ImageView icon = (ImageView) event.getSource();
            ScaleTransition scaleT = new ScaleTransition(Duration.millis(200), icon);
            scaleT.setToX(1);
            scaleT.setToY(1);
            scaleT.play();

            icon.setEffect(null);
        }
    }

    public void enterKeyPressed(KeyEvent keyEvent) {
    }

    public void genderOnAction(ActionEvent actionEvent) {
        if (rdoMale.isSelected()){
            gender = "Male";
            rdoFemale.setSelected(false);
        }
        if (rdoFemale.isSelected()){
            gender = "Female";
            rdoMale.setSelected(false);
        }
    }

    public void checkRoomBtnOnAction(ActionEvent actionEvent) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("/lk/ijse/d24hostel/view/RoomsAvailability.fxml"));
        Stage stage2 = new Stage();
        stage2.setScene(new Scene(parent));
        stage2.setResizable(false);
        stage2.show();
    }

    public void paymentOnAction(ActionEvent actionEvent) {
        if (rdoPayNow.isSelected()){
            payment = "Payed";
            rdoPayLater.setSelected(false);
        }
        if (rdoPayLater.isSelected()){
            payment = "Pending";
            rdoPayNow.setSelected(false);
        }
    }

    public void confirmBtnOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String id = generateReservationId();
        try {
            int q = reservationBO.searchRoomQty(String.valueOf(cmbRoomId.getValue()),"decrease");
            boolean b = roomsBO.updateRoom(new Rooms(String.valueOf(cmbRoomId.getValue()),txtRoomType.getText(), BigDecimal.valueOf(Double.parseDouble(txtKeyMoney.getText())),q));
            boolean save = reservationBO.saveReservation(new Reservation(id, lblDate.getText(), txtStudentId.getText(), String.valueOf(cmbRoomId.getValue()),payment));
            if (save && b){
                n.confirmMassage("Reservation Details saved Successfully","");
            }else {
                n.errorMassage("Deteils Not Saved", "Try Again...!");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void addStudentBtnOnAction(ActionEvent actionEvent) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("/lk/ijse/d24hostel/view/AddNewStudentForm.fxml"));
        Stage stage2 = new Stage();
        stage2.setScene(new Scene(parent));
        stage2.setResizable(false);
        stage2.show();
    }

    public String generateReservationId() throws SQLException, ClassNotFoundException {
            String id = reservationBO.generateNewReservationId();
            if (id != null){
                int newId = Integer.parseInt(id.replace("RES-",""))+1;
                return String.format("RES-%03d",newId);
            }else {
                return "RES-001";
            }
    }
}
