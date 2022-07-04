package lk.ijse.d24hostel.bo.custom.impl;

import lk.ijse.d24hostel.bo.custom.StudentBO;
import lk.ijse.d24hostel.dao.custom.StudentDAO;
import lk.ijse.d24hostel.dao.custom.impl.StudentDAOImpl;
import lk.ijse.d24hostel.dto.StudentDTO;
import lk.ijse.d24hostel.entity.Student;

import java.sql.SQLException;
import java.util.ArrayList;

public class StudentBOImpl implements StudentBO {
    StudentDAO studentDAO = new StudentDAOImpl();
    @Override
    public ArrayList<Student> getAllStudent() throws SQLException, ClassNotFoundException {
        return studentDAO.getAll();
    }

    @Override
    public ArrayList<Student> getAllStudentById(String id) throws SQLException, ClassNotFoundException {
        return studentDAO.getAllStudentsById(id);
    }

    @Override
    public ArrayList<String> getAllStudentIds() throws SQLException, ClassNotFoundException {
        return studentDAO.getIds();
    }

    @Override
    public boolean deleteStudent(String code) throws SQLException, ClassNotFoundException {
        return studentDAO.delete(code);
    }

    @Override
    public boolean saveStudent(Student dto) throws SQLException, ClassNotFoundException {
        studentDAO.save(dto);
        return false;
    }

    @Override
    public boolean updateStudent(Student dto) throws SQLException, ClassNotFoundException {
        return studentDAO.update(dto);
    }

    @Override
    public boolean existsStudent(String code) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public Student searchStudent(String code) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public String generateNewStudentId() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public ArrayList<String> searchStudentCode() throws SQLException, ClassNotFoundException {
        return null;
    }
}
