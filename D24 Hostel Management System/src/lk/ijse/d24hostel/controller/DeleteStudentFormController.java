package lk.ijse.d24hostel.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import lk.ijse.d24hostel.bo.custom.StudentBO;
import lk.ijse.d24hostel.bo.custom.impl.StudentBOImpl;
import lk.ijse.d24hostel.entity.Student;

import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class DeleteStudentFormController implements Initializable {

    public AnchorPane context;
    public JFXComboBox cmbStudentId;
    public JFXButton btnDelete;
    public JFXTextField txtName;
    public JFXTextField txtAddress;
    public JFXTextField txtContact;
    public JFXTextField txtDoB;
    public JFXTextField txtGender;
    StudentBO studentBO = new StudentBOImpl();
    NotificationController n = new NotificationController();

    public void deleteBtnOnAction(ActionEvent actionEvent) {
        try {
            boolean delete = studentBO.deleteStudent(String.valueOf(cmbStudentId.getValue()));
            if (delete){
                n.confirmMassage("Student Deleted Successfully...!", "");
            }else{
                n.errorMassage("Can't Delete Student","Try Again...");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setStudentIds();
    }

    private void setStudentIds() {
        try {

            ArrayList<String> ids = studentBO.getAllStudentIds();

            cmbStudentId.getItems().addAll(ids);


            cmbStudentId.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
                String code= (String) newValue;


                ArrayList<Student> students = null;

                try {
                    students = studentBO.getAllStudentById(code);
                    for (Student student : students) {
                        txtName.setText(student.getStudentName());
                        txtAddress.setText(student.getStudentAddress());
                        txtContact.setText(String.valueOf(student.getContact()));
                        txtDoB.setText(student.getDob());
                        txtGender.setText(student.getGender());
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
}
