package lk.ijse.d24hostel.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import lk.ijse.d24hostel.bo.custom.UserBO;
import lk.ijse.d24hostel.bo.custom.impl.UserBOImpl;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.ResourceBundle;

public class LoginPageFormController implements Initializable{
    public JFXTextField txtUserName;
    public JFXPasswordField txtPassword;
    public JFXButton btnSignIn;
    public JFXButton btnSignUp;
    public AnchorPane context;
    public JFXCheckBox checkBox;
    public JFXTextField txtPswd;
    public Label lblDate;
    public Label lblTime;

    UserBO userBO = new UserBOImpl();
    NotificationController ntfs = new NotificationController();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        txtPswd.setVisible(false);
        setDateAndTime();
    }

    private void setDateAndTime() {
        lblDate.setText(new SimpleDateFormat("yyyy-MM-dd, EEEE").format(new Date()));

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

    public void signInBtnOnAction(ActionEvent actionEvent) throws IOException, SQLException, ClassNotFoundException {
        String name = userBO.getUserIdByUserName(txtUserName.getText());
        String pswd = userBO.getUserIdByPassword(txtPassword.getText());
        if (name.equals(pswd)){
            Stage stage = (Stage) btnSignIn.getScene().getWindow();
            stage.close();

            Parent parent = FXMLLoader.load(getClass().getResource("/lk/ijse/d24hostel/view/DashBoardForm.fxml"));
            Stage stage2 = new Stage();
            stage2.setScene(new Scene(parent));
            stage2.setResizable(false);
            stage2.show();
            ntfs.confirmMassage("Login Successful...!", "Welcome to the D24 Hostel System");
        }else{
            ntfs.errorMassage("Login Failed...!", "Try again");
        }
    }

    public void signUpBtnOnAction(ActionEvent actionEvent) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("/lk/ijse/d24hostel/view/SignUpPageForm.fxml"));
        Stage stage2 = new Stage();
        stage2.setScene(new Scene(parent));
        stage2.setResizable(false);
        stage2.show();
    }

    public void showPasswordOnAction(ActionEvent actionEvent) {
        if (checkBox.isSelected()){
            txtPassword.setVisible(false);
            txtPswd.setVisible(true);
            txtPswd.setText(txtPassword.getText());
        }else {
            txtPswd.setVisible(false);
            txtPassword.setVisible(true);
        }
    }


}
