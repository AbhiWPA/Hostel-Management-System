package lk.ijse.d24hostel.controller;


import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.animation.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;
import lk.ijse.d24hostel.bo.custom.RoomsBO;
import lk.ijse.d24hostel.bo.custom.impl.RoomBOImpl;
import lk.ijse.d24hostel.entity.Rooms;
import lk.ijse.d24hostel.entity.Student;
import lk.ijse.d24hostel.util.ValidationUtil;

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
import java.util.LinkedHashMap;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

public class ManageRoomDetailsFormController implements Initializable {
    public AnchorPane context;
    public Label nonAc;
    public Label nonAcFood;
    public Label Ac;
    public Label AcFood;
    public JFXTextField txtRoomId;
    public JFXTextField txtKeyMoney;
    public JFXTextField txtQty;
    public JFXComboBox cmbRoomType;
    public JFXButton btnAddRoom;
    public JFXComboBox cmbRoomId;
    public JFXTextField txtUpdateKeyMoney;
    public JFXTextField txtUpdateQty;
    public JFXComboBox cmbUpdateRoomType;
    public JFXButton btnUpdateRoom;
    public JFXComboBox cmbDeleteRoomId;
    public JFXTextField txtDeleteKeyMoney;
    public JFXTextField txtDeleteQty;
    public JFXTextField txtDeleteRoomType;
    public JFXButton btnDeleteRoom;
    public ImageView imgHome;
    public Label lblDate;
    public Label lblTime;
    LinkedHashMap<TextField, Pattern> map = new LinkedHashMap<>();
    RoomsBO roomsBO = new RoomBOImpl();
    NotificationController n = new NotificationController();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setDateAndTime();

        String[] roomTypes= {"RM-1324", "RM-5467", "RM-7896", "RM-0093"};
        cmbRoomType.getItems().addAll(roomTypes);
        cmbUpdateRoomType.getItems().addAll(roomTypes);

        cmbRoomType.getSelectionModel().selectedItemProperty().
                addListener((observable, oldValue, newValue) -> {
                    if (newValue.equals("RM-1324")) {

                        txtRoomId.setText("Non-AC");
                        txtKeyMoney.setText("3100.00");

                    }else if (newValue.equals("RM-1324")){

                        txtRoomId.setText("Non-AC");
                        txtKeyMoney.setText("3100.00");

                    }else if (newValue.equals("RM-5467")){

                        txtRoomId.setText("Non-AC/Food");
                        txtKeyMoney.setText("6500.00");

                    }else if (newValue.equals("RM-7896")){

                        txtRoomId.setText("AC");
                        txtKeyMoney.setText("8900.00");

                    }else if (newValue.equals("RM-0093")){

                        txtRoomId.setText("AC / Food");
                        txtKeyMoney.setText("16000.00");
                    }
                });

        setRoomDetails();
        setRoomDetailsForDelete();
        setRoomQtys();

        Pattern qtyPattern = Pattern.compile("^[0-9]{1,100}$");

        map.put(txtQty,qtyPattern);
    }

    private void setRoomQtys() {
        try {
            ArrayList<Integer> NonAc = roomsBO.getRoomsQtyById("RM-1324");
            for (int q : NonAc ){
                int qty = q;
                nonAc.setText(String.valueOf(qty));
            }
            ArrayList<Integer> NonAcFood = roomsBO.getRoomsQtyById("RM-5467");
            for (int rooms : NonAcFood ){
                int qty = rooms;
                nonAcFood.setText(String.valueOf(qty));
            }
            ArrayList<Integer> ac = roomsBO.getRoomsQtyById("RM-7896");
            for (int rooms : ac ){
                int qty = rooms;
                Ac.setText(String.valueOf(qty));
            }
            ArrayList<Integer> acFood = roomsBO.getRoomsQtyById("RM-0093");
            for (int rooms : acFood ){
                int qty = rooms;
                AcFood.setText(String.valueOf(qty));
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    private void setRoomDetailsForDelete() {
        try {

            ArrayList<String> ids = roomsBO.getAllRoomIds();

            cmbDeleteRoomId.getItems().addAll(ids);


            cmbDeleteRoomId.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
                String code= (String) newValue;


                ArrayList<Rooms> rooms = null;

                try {
                    rooms = roomsBO.getAllRoomsByIds(code);
                    for (Rooms room : rooms) {
                        txtDeleteRoomType.setText(room.getRoomType());
                        txtDeleteKeyMoney.setText(String.valueOf(room.getKeyMoney()));
                        txtDeleteQty.setText(String.valueOf(room.getQty()));
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

    private void setRoomDetails() {
        try {

            ArrayList<String> ids = roomsBO.getAllRoomIds();

            cmbRoomId.getItems().addAll(ids);


            cmbRoomId.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
                String code= (String) newValue;


                ArrayList<Rooms> rooms = null;

                try {
                    rooms = roomsBO.getAllRoomsByIds(code);
                    for (Rooms room : rooms) {
                        cmbUpdateRoomType.setValue(room.getRoomType());
                        txtUpdateKeyMoney.setText(String.valueOf(room.getKeyMoney()));
                        txtUpdateQty.setText(String.valueOf(room.getQty()));
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
                    loadUi("DashBoardForm", imgHome);
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

    public void btnAddRoomOnAction(ActionEvent actionEvent) {
        try {
            boolean save = roomsBO.saveRoom(new Rooms(txtRoomId.getText(), String.valueOf(cmbRoomType.getValue()), BigDecimal.valueOf(Double.parseDouble(txtKeyMoney.getText())), Integer.parseInt(txtQty.getText())));
            if (save){
                n.confirmMassage("Room Details added Successfully...", "");
            }else {
                n.errorMassage("Can't Save","Try again....!");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void updateRoomBtnOnAction(ActionEvent actionEvent) {
        try {
            boolean update = roomsBO.updateRoom(new Rooms(String.valueOf(cmbRoomId.getValue()), String.valueOf(cmbUpdateRoomType.getValue()), BigDecimal.valueOf(Double.parseDouble(txtUpdateKeyMoney.getText())), Integer.parseInt(txtUpdateQty.getText())));
            if (update){
                n.confirmMassage("Room Details Updated Successfully....","");
            }else {
                n.errorMassage("can't Update", "Try Again...");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void deleteRoomBtnOnAction(ActionEvent actionEvent) {
        try {
            boolean delete = roomsBO.deleteRoom(String.valueOf(cmbDeleteRoomId.getValue()));
            if (delete){
                n.confirmMassage("Room Deleted SuccessFully....", "");
            }else {
                n.errorMassage("Can't Delete..","Try Again...!");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void loadUi(String location, ImageView img) throws IOException {
        Stage stage = (Stage) img.getScene().getWindow();
        stage.close();

        Parent parent = FXMLLoader.load(getClass().getResource("/lk/ijse/d24hostel/view/"+location+".fxml"));
        Stage stage2 = new Stage();
        stage2.setScene(new Scene(parent));
        stage2.setResizable(false);
        stage2.show();
    }


    public void enterKeyPressed(KeyEvent keyEvent) {
        ValidationUtil.validate(map,btnAddRoom);
        ValidationUtil.validate(map,btnUpdateRoom);

        if (keyEvent.getCode() == KeyCode.ENTER) {
            Object response =  ValidationUtil.validate(map,btnAddRoom);
            if (response instanceof TextField) {
                TextField textField = (TextField) response;
                textField.requestFocus();
            } else if (response instanceof Boolean) {
                System.out.println("Work");
            }
        }
    }
}
