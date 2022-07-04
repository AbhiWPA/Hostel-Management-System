package lk.ijse.d24hostel.bo.custom;

import lk.ijse.d24hostel.bo.SuperBO;
import lk.ijse.d24hostel.dto.StudentDTO;
import lk.ijse.d24hostel.entity.Student;

import java.sql.SQLException;
import java.util.ArrayList;

public interface StudentBO extends SuperBO {
    ArrayList<Student> getAllStudent() throws SQLException, ClassNotFoundException;

    ArrayList<Student> getAllStudentById(String id) throws SQLException, ClassNotFoundException;

    ArrayList<String> getAllStudentIds() throws SQLException, ClassNotFoundException;

    boolean deleteStudent(String code) throws SQLException, ClassNotFoundException;

    boolean saveStudent(Student dto) throws SQLException, ClassNotFoundException;

    boolean updateStudent(Student dto) throws SQLException, ClassNotFoundException;

    boolean existsStudent(String code) throws SQLException, ClassNotFoundException;

    Student searchStudent(String code) throws SQLException, ClassNotFoundException;

    String generateNewStudentId() throws SQLException, ClassNotFoundException;

    ArrayList<String> searchStudentCode() throws SQLException, ClassNotFoundException;
}
