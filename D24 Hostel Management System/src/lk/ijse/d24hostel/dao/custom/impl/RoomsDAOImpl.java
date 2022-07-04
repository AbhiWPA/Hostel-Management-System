package lk.ijse.d24hostel.dao.custom.impl;

import lk.ijse.d24hostel.dao.custom.RoomsDAO;
import lk.ijse.d24hostel.dao.custom.StudentDAO;
import lk.ijse.d24hostel.dto.RoomsDTO;
import lk.ijse.d24hostel.entity.Rooms;
import lk.ijse.d24hostel.entity.Student;
import lk.ijse.d24hostel.util.FactoryConfiguration;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RoomsDAOImpl implements RoomsDAO {
    Session session;
    Transaction transaction;

    @Override
    public ArrayList<Rooms> getAllRoomsById(String Id) throws ClassNotFoundException, SQLException {
        session = FactoryConfiguration.getInstance().getSessionFactory();
        transaction = session.beginTransaction();
        Query student = session.createQuery("FROM Rooms WHERE rID = :rId");
        student.setParameter("rId", Id);
        List<Rooms> rooms = student.list();
        transaction.commit();
        session.close();
        return (ArrayList<Rooms>) rooms;
    }

    @Override
    public ArrayList<Integer> getRoomsQtyById(String Id) throws ClassNotFoundException, SQLException {
        session = FactoryConfiguration.getInstance().getSessionFactory();
        transaction = session.beginTransaction();
        Query qty = session.createQuery("SELECT qty FROM Rooms WHERE rID = :rId");
        qty.setParameter("rId", Id);
        List<Integer> rooms = qty.list();
        transaction.commit();
        session.close();
        return (ArrayList<Integer>) rooms;
    }

    @Override
    public ArrayList<Rooms> getAll() throws SQLException, ClassNotFoundException {
        session = FactoryConfiguration.getInstance().getSessionFactory();
        transaction = session.beginTransaction();
        Query rooms = session.createQuery("FROM Rooms ");
        List<Rooms> roomsList = rooms.list();
        transaction.commit();
        session.close();
        return (ArrayList<Rooms>) roomsList;
    }

    @Override
    public boolean save(Rooms dto) throws SQLException, ClassNotFoundException {
        session = FactoryConfiguration.getInstance().getSessionFactory();
        transaction = session.beginTransaction();
        session.save(dto);
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public boolean update(Rooms dto) throws SQLException, ClassNotFoundException {
        session = FactoryConfiguration.getInstance().getSessionFactory();
        transaction = session.beginTransaction();
        Query query = session.createQuery("UPDATE Rooms SET roomType = :type, keyMoney= :money, qty= :qty WHERE rID = :id");
        query.setParameter("type", dto.getRoomType());
        query.setParameter("money", dto.getKeyMoney());
        query.setParameter("qty", dto.getQty());
        query.setParameter("id", dto.getRID());
        query.executeUpdate();
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public Rooms search(String s) throws SQLException, ClassNotFoundException {
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
        Query delete = session.createQuery("DELETE FROM Rooms WHERE rID= :ID");
        delete.setParameter("ID", s);
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
        Query ids = session.createQuery("SELECT rID FROM Rooms ");
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
