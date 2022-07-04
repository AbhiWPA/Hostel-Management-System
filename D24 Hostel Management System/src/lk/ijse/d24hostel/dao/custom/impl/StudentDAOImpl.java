package lk.ijse.d24hostel.dao.custom.impl;

import lk.ijse.d24hostel.dao.custom.StudentDAO;
import lk.ijse.d24hostel.dto.StudentDTO;
import lk.ijse.d24hostel.entity.Student;
import lk.ijse.d24hostel.util.FactoryConfiguration;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentDAOImpl implements StudentDAO {
    Session session;
    Transaction transaction;
    @Override
    public ArrayList<Student> getAllStudentsById(String Id) throws ClassNotFoundException, SQLException {
        session = FactoryConfiguration.getInstance().getSessionFactory();
        transaction = session.beginTransaction();
        Query student = session.createQuery("FROM Student WHERE studentId = :studentId");
        student.setParameter("studentId", Id);
        List<Student> students = student.list();
        transaction.commit();
        session.close();
        return (ArrayList<Student>) students;
    }

    @Override
    public ArrayList<Student> getAll() throws SQLException, ClassNotFoundException {
        session = FactoryConfiguration.getInstance().getSessionFactory();
        transaction = session.beginTransaction();
        Query students = session.createQuery("FROM Student ");
        List<Student> studentList = students.list();
        transaction.commit();
        session.close();
        return (ArrayList<Student>) studentList;
    }

    @Override
    public boolean save(Student dto) throws SQLException, ClassNotFoundException {
        session = FactoryConfiguration.getInstance().getSessionFactory();
        transaction = session.beginTransaction();
        session.save(dto);
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public boolean update(Student dto) throws SQLException, ClassNotFoundException {
        session = FactoryConfiguration.getInstance().getSessionFactory();
        transaction = session.beginTransaction();
        Query query = session.createQuery("UPDATE Student SET studentName = :name, studentAddress= :address, contact= :contact, dob= :birth, gender= :gender WHERE studentId = :id");
        query.setParameter("name", dto.getStudentName());
        query.setParameter("address", dto.getStudentAddress());
        query.setParameter("contact", dto.getContact());
        query.setParameter("birth", dto.getDob());
        query.setParameter("gender", dto.getGender());
        query.setParameter("id", dto.getStudentId());
        query.executeUpdate();
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public Student search(String s) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean exist(String s) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(String s) throws SQLException, ClassNotFoundException {
        session = FactoryConfiguration.getInstance().getSessionFactory();
        transaction = session.beginTransaction();
        Query delete = session.createQuery("DELETE FROM Student WHERE studentId= :ID");
        delete.setParameter("ID", s);
        //session.delete(s);
        delete.executeUpdate();
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public String generateNewID() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public ArrayList<String> getIds() throws SQLException, ClassNotFoundException {
        session = FactoryConfiguration.getInstance().getSessionFactory();
        transaction = session.beginTransaction();
        Query ids = session.createQuery("SELECT studentId FROM Student");
        List<String> idList = ids.list();
        transaction.commit();
        session.close();
        return (ArrayList<String>) idList;
    }

    @Override
    public boolean updateQty(int qty, String code) throws SQLException, ClassNotFoundException {
        return false;
    }
}
