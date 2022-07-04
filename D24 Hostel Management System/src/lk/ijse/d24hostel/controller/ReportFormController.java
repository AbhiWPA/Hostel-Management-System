package lk.ijse.d24hostel.controller;

import com.jfoenix.controls.*;
import javafx.animation.*;
import javafx.event.ActionEvent;
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
import lk.ijse.d24hostel.bo.custom.ReservationBO;
import lk.ijse.d24hostel.bo.custom.RoomsBO;
import lk.ijse.d24hostel.bo.custom.impl.ReservationBOImpl;
import lk.ijse.d24hostel.bo.custom.impl.RoomBOImpl;
import lk.ijse.d24hostel.entity.Reservation;
import lk.ijse.d24hostel.entity.Student;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;

public class ReportFormController implements Initializable {
    public AnchorPane context;
    public ImageView imgHome;
    public TableView tblReservations;
    public TableColumn colReservationId;
    public TableColumn colDate;
    public TableColumn colStudentId;
    public TableColumn colRoomId;
    public TableColumn colPayments;
    public JFXTextField txtStudentId;
    public JFXTextField txtRoomId;
    public Label lblPayments;
    public JFXRadioButton rdoPay;
    public JFXRadioButton rdoLater;
    public JFXButton btnUpdate;
    public JFXButton btnDelete;
    public Label lblDate;
    public Label lblTime;
    public JFXDatePicker datePicker;
    public JFXComboBox cmbReservationId;
    String payment;
    NotificationController n = new NotificationController();
    ReservationBO reservationBO = new ReservationBOImpl();
    RoomsBO roomsBO = new RoomBOImpl();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setDateAndTime();
        setTableDetails();

        colReservationId.setCellValueFactory(new PropertyValueFactory<>("ReservationId"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colStudentId.setCellValueFactory(new PropertyValueFactory<>("StudentId"));
        colRoomId.setCellValueFactory(new PropertyValueFactory<>("RoomId"));
        colPayments.setCellValueFactory(new PropertyValueFactory<>("Payments"));

        setReservationDetails();

//        tblReservations.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
//            btnDelete.setDisable(newValue == null);
//            btnSave.setText(newValue != null ? "Update" : "Save");
//            btnSave.setDisable(newValue == null);
//
//            if (newValue != null) {
//                txtReservationId.setText(newValue.getCode());
//                txtDescription.setText(newValue.getDescription());
//                txtUnitPrice.setText(newValue.getUnitPrice().setScale(2).toString());
//                txtQtyOnHand.setText(newValue.getQtyOnHand() + "");
//
//                txtCode.setDisable(false);
//                txtDescription.setDisable(false);
//                txtUnitPrice.setDisable(false);
//                txtQtyOnHand.setDisable(false);
//            }
//        });
    }

    private void setReservationDetails() {
        try {

            ArrayList<String> ids = reservationBO.getReservationIds();

            cmbReservationId.getItems().addAll(ids);


            cmbReservationId.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
                String code= (String) newValue;


                ArrayList<Reservation> reservations = null;

                try {
                    reservations = reservationBO.getAllReservationsById(code);
                    for (Reservation reservation : reservations) {
                        datePicker.setValue(LocalDate.parse(String.valueOf(reservation.getDate())));
                        txtStudentId.setText(reservation.getStudentId());
                        txtRoomId.setText(reservation.getRoomId());
                        lblPayments.setText(reservation.getPayments());
                        if (lblPayments.getText().equals("Payed")){
                            rdoPay.setSelected(true);
                        }else if (lblPayments.getText().equals("Pending")){
                            rdoLater.setSelected(true);
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

    private void setTableDetails() {
        tblReservations.getItems().clear();
        try {
            ArrayList<Reservation> allReservations = reservationBO.getAllReservations();

            for (Reservation reservation : allReservations) {
                tblReservations.getItems().add(new Reservation(reservation.getReservationId(), reservation.getDate(), reservation.getStudentId(), reservation.getRoomId(), reservation.getPayments()));
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

    public void paymentOnAction(ActionEvent actionEvent) {
        if (rdoPay.isSelected()){
            lblPayments.setText("Payed");
            payment = "Payed";
            rdoLater.setSelected(false);
        }else if (rdoLater.isSelected()){
            lblPayments.setText("Pending");
            payment = "Pending";
            rdoPay.setSelected(false);
        }else{
            lblPayments.setText("Pending/Payed");
        }
    }

    public void updateBtnOnAction(ActionEvent actionEvent) {
        try {
            boolean update = reservationBO.updateReservation(new Reservation(String.valueOf(cmbReservationId.getValue()), String.valueOf(datePicker.getValue()), txtStudentId.getText(), txtRoomId.getText(), payment));
            if (update){
                n.confirmMassage("Reservation Details Updated Successfully...!", "");
                setTableDetails();
            } else {
                n.errorMassage("Can't Update", "Try Again...!");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void deleteBtnOnAction(ActionEvent actionEvent) {
        try {
            boolean delete = reservationBO.deleteReservation(String.valueOf(cmbReservationId.getValue()));
            if (delete){
                n.confirmMassage("Reservation details Deleted Successfully...!", "");
                setTableDetails();
            }else {
                n.errorMassage("Can't Delete Reservation Details", "Try Again...!");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
