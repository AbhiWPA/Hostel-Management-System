package lk.ijse.d24hostel.controller;

import com.jfoenix.controls.*;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import lk.ijse.d24hostel.bo.custom.StudentBO;
import lk.ijse.d24hostel.bo.custom.impl.StudentBOImpl;
import lk.ijse.d24hostel.dto.StudentDTO;
import lk.ijse.d24hostel.entity.Student;
import lk.ijse.d24hostel.util.FactoryConfiguration;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class UpdateStudentDetailsFormController implements Initializable {
    public AnchorPane context;
    public JFXTextField txtName;
    public JFXTextField txtAddress;
    public JFXTextField txtContact;
    public JFXDatePicker datePicker;
    public JFXComboBox cmbStudentId;
    public JFXRadioButton maleBtn;
    public JFXRadioButton femaleBtn;
    public JFXButton btnUpdate;
    private String gender;
    NotificationController n = new NotificationController();
    StudentBO studentBO = new StudentBOImpl();

    public void updateBtnOnAction(ActionEvent actionEvent) {
        try {
            boolean update = studentBO.updateStudent(new Student(String.valueOf(cmbStudentId.getValue()), txtName.getText(), txtAddress.getText(), Integer.parseInt(txtContact.getText()), String.valueOf(datePicker.getValue()), gender));
            if (update){
                n.confirmMassage("Student Details Updated Successfully", "");
            }else {
                n.errorMassage("Can't Update Details","Try Again....");
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
                        datePicker.setValue(LocalDate.parse(student.getDob()));
                        if (student.getGender().equals("Male")){
                            maleBtn.setSelected(true);
                        }else if (student.getGender().equals("Female")){
                            femaleBtn.setSelected(true);
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
    }

