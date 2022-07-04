package lk.ijse.d24hostel.bo.custom.impl;

import lk.ijse.d24hostel.bo.custom.ReservationBO;
import lk.ijse.d24hostel.dao.custom.ReservationDAO;
import lk.ijse.d24hostel.dao.custom.impl.ReservationDAOImpl;
import lk.ijse.d24hostel.dto.ReservationDTO;
import lk.ijse.d24hostel.entity.Reservation;
import lk.ijse.d24hostel.entity.Rooms;
import lk.ijse.d24hostel.entity.Student;

import java.sql.SQLException;
import java.util.ArrayList;

public class ReservationBOImpl implements ReservationBO {
    ReservationDAO reservationDAO = new ReservationDAOImpl();

    @Override
    public ArrayList<Reservation> getAllReservations() throws SQLException, ClassNotFoundException {
        return reservationDAO.getAll();
    }

    @Override
    public ArrayList<String> getReservationIds() throws SQLException, ClassNotFoundException {
        return reservationDAO.getIds();
    }

    @Override
    public ArrayList<Reservation> getAllReservationsById(String Id) throws ClassNotFoundException, SQLException {
        return reservationDAO.getAllRoomReservationsById(Id);
    }

    @Override
    public ArrayList<Reservation> getAllStudent() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean deleteReservation(String code) throws SQLException, ClassNotFoundException {
        return reservationDAO.delete(code);
    }

    @Override
    public boolean saveReservation(Reservation dto) throws SQLException, ClassNotFoundException {
        return reservationDAO.save(dto);
    }

    @Override
    public boolean updateReservation(Reservation dto) throws SQLException, ClassNotFoundException {
        return reservationDAO.update(dto);
    }

    @Override
    public boolean existsReservation(String code) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public Reservation searchReservation(String code) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public String generateNewReservationId() throws SQLException, ClassNotFoundException {
        return reservationDAO.generateNewID();
    }

    @Override
    public ArrayList<String> searchReservationCode() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public ArrayList<Student> getAllStudentsById(String Id) throws ClassNotFoundException, SQLException {
        return reservationDAO.getAllStudentsById(Id);
    }

    @Override
    public ArrayList<String> getStudentIds() throws SQLException, ClassNotFoundException {
        return reservationDAO.getStudentIds();
    }

    @Override
    public ArrayList<String> getRoomIds() throws SQLException, ClassNotFoundException {
        return reservationDAO.getRoomIds();
    }

    @Override
    public ArrayList<Rooms> getAllRoomsById(String Id) throws ClassNotFoundException, SQLException {
        return reservationDAO.getAllRoomsById(Id);
    }

    @Override
    public int searchRoomQty(String code, String name) throws Exception {
        return reservationDAO.searchRoomQty(code, name);
    }

    @Override
    public int updateRoomQty(String code, String name) throws Exception {
        return reservationDAO.updateRoomQty(code, name);
    }
}
