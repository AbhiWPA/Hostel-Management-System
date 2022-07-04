package lk.ijse.d24hostel.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import lk.ijse.d24hostel.bo.custom.UserBO;
import lk.ijse.d24hostel.bo.custom.impl.UserBOImpl;
import lk.ijse.d24hostel.dao.custom.impl.UserDAOImpl;
import lk.ijse.d24hostel.entity.User;

import java.sql.SQLException;

public class SignUpPageFormController {
    public JFXTextField txtUserName;
    public JFXTextField txtMail;
    public JFXPasswordField txtRePassword;
    public JFXPasswordField txtPassword;
    public JFXButton btnSignUp;
    public AnchorPane context;
    NotificationController n = new NotificationController();
    UserBO userBO = new UserBOImpl();

    public void signUpBtnOnAction(ActionEvent actionEvent) {

        try {
            String id = generateNewId();
            if (txtPassword.getText().equals(txtRePassword.getText())) {
                boolean save = userBO.saveUser(new User(id,txtUserName.getText(), txtPassword.getText()));
                if (save){
                    n.confirmMassage("Sign Up Successfully","Login to the System Your ow Username & Password");
                }else {
                    n.errorMassage("Can't Add new User", "Try again Later");
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void keyRelesed(KeyEvent keyEvent) {
    }

    public String generateNewId() throws SQLException, ClassNotFoundException {
        String id = userBO.generateNewUserId();
        if (id != null){
            int newId = Integer.parseInt(id.replace("U-",""))+1;
            return String.format("U-%03d",newId);
        }else {
            return "U-001";
        }
    }
}
