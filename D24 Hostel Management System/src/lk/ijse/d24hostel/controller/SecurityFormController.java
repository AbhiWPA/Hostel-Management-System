package lk.ijse.d24hostel.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
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
import lk.ijse.d24hostel.bo.custom.UserBO;
import lk.ijse.d24hostel.bo.custom.impl.UserBOImpl;
import lk.ijse.d24hostel.dao.custom.UserDAO;
import lk.ijse.d24hostel.dao.custom.impl.UserDAOImpl;
import lk.ijse.d24hostel.entity.User;
import lk.ijse.d24hostel.util.ValidationUtil;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

public class SecurityFormController implements Initializable {
    public AnchorPane context;
    public ImageView imgHome;
    public JFXTextField txtCurrentUserName;
    public JFXPasswordField txtCurrentPswd;
    public JFXButton btnNext;
    public JFXTextField txtNewUserName;
    public JFXPasswordField txtNewPassword;
    public JFXPasswordField txtConfirmPswd;
    public JFXButton btnUpdate;
    LinkedHashMap<TextField, Pattern> map = new LinkedHashMap<>();
    NotificationController n = new NotificationController();
    UserBO userBO = new UserBOImpl();

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

    public void nextBtnOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String name = userBO.getUserIdByUserName(txtCurrentUserName.getText());
        String pswd = userBO.getUserIdByPassword(txtCurrentPswd.getText());

        if (name.equals(pswd)){
            txtNewUserName.setDisable(false);
            txtNewPassword.setDisable(false);
            txtConfirmPswd.setDisable(false);
        }
    }

    public void enterKeyPressed(KeyEvent keyEvent) {
        ValidationUtil.validate(map,btnUpdate);

        if (keyEvent.getCode() == KeyCode.ENTER) {
            Object response =  ValidationUtil.validate(map,btnUpdate);
            if (response instanceof TextField) {
                TextField textField = (TextField) response;
                textField.requestFocus();// if there is a error just focus it
            } else if (response instanceof Boolean) {
                System.out.println("Work");
            }
        }
    }
// comment
    public void updateBtnOnAction(ActionEvent actionEvent) {
        try {
            if (txtNewPassword.getText().equals(txtConfirmPswd.getText())){
                String id = userBO.getUserIdByUserName(txtCurrentUserName.getText());
                boolean update = userBO.updateUser(new User(id, txtNewUserName.getText(), txtNewPassword.getText()));

                if (update){
                    n.confirmMassage("User Details Updated Successfully...!","");
                }else {
                    n.errorMassage("Cant Update User name & Password...!","Try Again...");
                }
            }else {
                n.errorMassage("Check your New Password", "");
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        txtNewUserName.setDisable(true);
        txtNewPassword.setDisable(true);
        txtConfirmPswd.setDisable(true);

        Pattern name = Pattern.compile("^[A-z ]{3,15}$");
        Pattern pswd = Pattern.compile("^[A-z0-9]{8,}$");

        map.put(txtNewUserName,name);
        map.put(txtNewPassword,pswd);
    }
}
