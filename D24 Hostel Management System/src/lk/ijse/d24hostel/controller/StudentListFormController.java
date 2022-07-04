package lk.ijse.d24hostel.controller;

import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import lk.ijse.d24hostel.bo.custom.StudentBO;
import lk.ijse.d24hostel.bo.custom.impl.StudentBOImpl;
import lk.ijse.d24hostel.entity.Student;
import net.bytebuddy.dynamic.DynamicType;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class StudentListFormController implements Initializable {
    public AnchorPane context;
    public TableView tblStudent;
    public TableColumn colStId;
    public TableColumn colName;
    public TableColumn colAddress;
    public TableColumn colDob;
    public TableColumn colContact;
    public TableColumn colGender;
    StudentBO studentBO = new StudentBOImpl();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
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
}
