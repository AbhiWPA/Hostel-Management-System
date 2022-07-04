package lk.ijse.d24hostel.dao.custom;

import lk.ijse.d24hostel.dao.CrudDAO;
import lk.ijse.d24hostel.dto.StudentDTO;
import lk.ijse.d24hostel.entity.Student;

import java.sql.SQLException;
import java.util.ArrayList;

public interface StudentDAO extends CrudDAO<Student, String> {
    ArrayList<Student> getAllStudentsById(String Id)throws ClassNotFoundException, SQLException;
}
