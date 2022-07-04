package lk.ijse.d24hostel.dao.custom.impl;

import lk.ijse.d24hostel.dao.custom.StudentDAO;
import lk.ijse.d24hostel.dao.custom.UserDAO;
import lk.ijse.d24hostel.dto.UserDTO;
import lk.ijse.d24hostel.entity.Reservation;
import lk.ijse.d24hostel.entity.Student;
import lk.ijse.d24hostel.entity.User;
import lk.ijse.d24hostel.util.FactoryConfiguration;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDAOImpl implements UserDAO {
    Session session;
    Transaction transaction;
    @Override
    public ArrayList<UserDAO> getAllUsersById(String Id) throws ClassNotFoundException, SQLException {
        return null;
    }

    @Override
    public String getUserIdByUserName(String name) throws ClassNotFoundException, SQLException {
        session = FactoryConfiguration.getInstance().getSessionFactory();
        transaction = session.beginTransaction();
        Query student = session.createQuery("FROM User WHERE userName = :name");
        student.setParameter("name", name);
        List<User> users = student.list();
        for (User user : users){
            String id = user.getUserID();
            return id;
        }
        transaction.commit();
        session.close();
        return null;
    }

    @Override
    public String getUserIdByPassword(String pswd) throws ClassNotFoundException, SQLException {
        session = FactoryConfiguration.getInstance().getSessionFactory();
        transaction = session.beginTransaction();
        Query student = session.createQuery("FROM User WHERE userPassword = :pswd");
        student.setParameter("pswd", pswd);
        List<User> users = student.list();
        for (User user : users){
            String id = user.getUserID();
            return id;
        }
        transaction.commit();
        session.close();
        return null;
    }

    @Override
    public ArrayList<User> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean save(User dto) throws SQLException, ClassNotFoundException {
        session = FactoryConfiguration.getInstance().getSessionFactory();
        transaction = session.beginTransaction();
        session.save(dto);
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public boolean update(User dto) throws SQLException, ClassNotFoundException {
        session = FactoryConfiguration.getInstance().getSessionFactory();
        transaction = session.beginTransaction();
        Query query = session.createQuery("UPDATE User SET userName = :name, userPassword= :pswd WHERE userID = :id");
        query.setParameter("name", dto.getUserName());
        query.setParameter("pswd", dto.getUserPassword());
        query.executeUpdate();
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public User search(String s) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean exist(String s) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(String s) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public String generateNewID() throws SQLException, ClassNotFoundException {
        session = FactoryConfiguration.getInstance().getSessionFactory();
        transaction = session.beginTransaction();
        List<Reservation> list = session.createQuery("FROM User ORDER BY userID DESC").list();
        for (Reservation reservation : list){
            String lastId = reservation.getReservationId();
            return lastId;
        }
        transaction.commit();
        session.close();
        return null;
    }

    @Override
    public ArrayList<String> getIds() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean updateQty(int qty, String code) throws SQLException, ClassNotFoundException {
        return false;
    }
}
