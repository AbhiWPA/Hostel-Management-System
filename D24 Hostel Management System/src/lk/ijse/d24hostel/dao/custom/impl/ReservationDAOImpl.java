package lk.ijse.d24hostel.dao.custom.impl;

import lk.ijse.d24hostel.dao.custom.ReservationDAO;
import lk.ijse.d24hostel.dao.custom.StudentDAO;
import lk.ijse.d24hostel.dto.ReservationDTO;
import lk.ijse.d24hostel.entity.Reservation;
import lk.ijse.d24hostel.entity.Rooms;
import lk.ijse.d24hostel.entity.Student;
import lk.ijse.d24hostel.util.FactoryConfiguration;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ReservationDAOImpl implements ReservationDAO {
    Session session;
    Transaction transaction;
    @Override
    public ArrayList<Reservation> getAllRoomReservationsById(String Id) throws ClassNotFoundException, SQLException {
        session = FactoryConfiguration.getInstance().getSessionFactory();
        transaction = session.beginTransaction();
        Query reservations = session.createQuery("FROM Reservation WHERE ReservationId = :resID");
        reservations.setParameter("resID", Id);
        List<Reservation> reservationList = reservations.list();
        transaction.commit();
        session.close();
        return (ArrayList<Reservation>) reservationList;
    }

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
    public ArrayList<String> getStudentIds() throws SQLException, ClassNotFoundException {
        session = FactoryConfiguration.getInstance().getSessionFactory();
        transaction = session.beginTransaction();
        Query ids = session.createQuery("SELECT studentId FROM Student");
        List<String> idList = ids.list();
        transaction.commit();
        session.close();
        return (ArrayList<String>) idList;
    }

    @Override
    public ArrayList<String> getRoomIds() throws SQLException, ClassNotFoundException {
        session = FactoryConfiguration.getInstance().getSessionFactory();
        transaction = session.beginTransaction();
        Query ids = session.createQuery("SELECT rID FROM Rooms ");
        List<String> idList = ids.list();
        transaction.commit();
        session.close();
        return (ArrayList<String>) idList;
    }

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
    public int searchRoomQty(String code, String name) throws Exception {
        int qt;
        session = FactoryConfiguration.getInstance().getSessionFactory();
        transaction = session.beginTransaction();
        String hql = "FROM Rooms WHERE rID =:room_id";
        Query query = session.createQuery(hql);
        query.setParameter("room_id", code);
        List<Rooms> roomList = query.list();

        for (Rooms room : roomList) {
            qt = room.getQty();
            int newQty = qt - 1;
            return newQty;
        }
        transaction.commit();
        session.close();
        return 0;
    }

    @Override
    public int updateRoomQty(String code, String name) throws Exception {
        int qt;
        session = FactoryConfiguration.getInstance().getSessionFactory();
        transaction = session.beginTransaction();
        String hql = "FROM Rooms WHERE rID =:room_id";
        Query query = session.createQuery(hql);
        query.setParameter("room_id", code);
        List<Rooms> roomList = query.list();

        for (Rooms room : roomList) {
            qt = room.getQty();
            int newQty = qt + 1;
            return newQty;
        }
        transaction.commit();
        session.close();
        return 0;
    }

    @Override
    public ArrayList<Reservation> getAll() throws SQLException, ClassNotFoundException {
        session = FactoryConfiguration.getInstance().getSessionFactory();
        transaction = session.beginTransaction();
        Query reservations = session.createQuery("FROM Reservation ");
        List<Reservation> reservationList = reservations.list();
        transaction.commit();
        session.close();
        return (ArrayList<Reservation>) reservationList;
    }

    @Override
    public boolean save(Reservation dto) throws SQLException, ClassNotFoundException {
        session = FactoryConfiguration.getInstance().getSessionFactory();
        transaction = session.beginTransaction();
        session.save(dto);
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public boolean update(Reservation dto) throws SQLException, ClassNotFoundException {
        session = FactoryConfiguration.getInstance().getSessionFactory();
        transaction = session.beginTransaction();
        Query query = session.createQuery("UPDATE Reservation SET date = :date, StudentId= :stId, RoomId= :rId, Payments= :payment WHERE ReservationId = :id");
        query.setParameter("date", dto.getDate());
        query.setParameter("stId", dto.getStudentId());
        query.setParameter("rId", dto.getRoomId());
        query.setParameter("payment", dto.getPayments());
        query.setParameter("id", dto.getStudentId());
        session.update(dto);
        query.executeUpdate();
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public Reservation search(String s) throws SQLException, ClassNotFoundException {
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
        Query delete = session.createQuery("DELETE FROM Reservation WHERE ReservationId= :ID");
        delete.setParameter("ID", s);
        //session.delete(s);
        delete.executeUpdate();
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public String generateNewID() throws SQLException, ClassNotFoundException {
        session = FactoryConfiguration.getInstance().getSessionFactory();
        transaction = session.beginTransaction();
        List<Reservation> list = session.createQuery("FROM Reservation ORDER BY ReservationId DESC").list();
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
        session = FactoryConfiguration.getInstance().getSessionFactory();
        transaction = session.beginTransaction();
        Query ids = session.createQuery("SELECT ReservationId FROM Reservation ");
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
