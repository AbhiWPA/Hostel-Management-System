package lk.ijse.d24hostel.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import lk.ijse.d24hostel.bo.custom.StudentBO;
import lk.ijse.d24hostel.bo.custom.impl.StudentBOImpl;
import lk.ijse.d24hostel.entity.Student;
import lk.ijse.d24hostel.util.ValidationUtil;

import java.net.URL;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.LinkedHashMap;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

public class AddNewStudentFormController implements Initializable {
    public AnchorPane context;
    public JFXTextField txtId;
    public JFXTextField txtName;
    public JFXTextField txtAddress;
    public JFXTextField txtContact;
    public JFXDatePicker datePicker;
    public JFXRadioButton maleBtn;
    public JFXRadioButton femaleBtn;
    public JFXButton btnSave;
    private String gender;
    LinkedHashMap<TextField, Pattern> map = new LinkedHashMap<>();
    StudentBO studentBO = new StudentBOImpl();
    NotificationController nfc = new NotificationController();

    public void saveBtnOnAction(ActionEvent actionEvent) {
        try {
            boolean save = studentBO.saveStudent(new Student(txtId.getText(), txtName.getText(), txtAddress.getText(), Integer.parseInt(txtContact.getText()), String.valueOf(datePicker.getValue()), gender));
            if (save){
                nfc.confirmMassage("Student Save Successful...","");
            }else {
                nfc.errorMassage("Not Saved", "Try Again...!");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void genderOnAction(ActionEvent actionEvent) {
        if (maleBtn.isSelected()){
            gender = "Male";
            femaleBtn.setSelected(false);
        }
        if (femaleBtn.isSelected()){
            gender = "Female";
            maleBtn.setSelected(false);
        }
    }

    public void enterKeyPressed(KeyEvent keyEvent) {
        ValidationUtil.validate(map,btnSave);

        if (keyEvent.getCode() == KeyCode.ENTER) {
            Object response =  ValidationUtil.validate(map,btnSave);
            if (response instanceof TextField) {
                TextField textField = (TextField) response;
                textField.requestFocus();
            } else if (response instanceof Boolean) {
                System.out.println("Work");
            }
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Pattern idPattern = Pattern.compile("^(S00)[1-9]{1,3}$");
        Pattern namePattern = Pattern.compile("^[A-z ]{3,15}$");
        Pattern addressPattern = Pattern.compile("^[A-z0-9 ,/]{4,20}$");
        Pattern contactPattern = Pattern.compile("^(070|071|072|075|076|077|078|027)[0-9]{7}$");

        map.put(txtId,idPattern);
        map.put(txtName,namePattern);
        map.put(txtAddress,addressPattern);
        map.put(txtContact,contactPattern);
    }
}
