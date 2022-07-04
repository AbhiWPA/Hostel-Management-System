package lk.ijse.d24hostel.bo.custom;

import lk.ijse.d24hostel.bo.SuperBO;
import lk.ijse.d24hostel.dto.ReservationDTO;
import lk.ijse.d24hostel.dto.StudentDTO;
import lk.ijse.d24hostel.entity.Reservation;
import lk.ijse.d24hostel.entity.Rooms;
import lk.ijse.d24hostel.entity.Student;

import java.sql.SQLException;
import java.util.ArrayList;

public interface ReservationBO extends SuperBO {
    ArrayList<Reservation> getAllReservations() throws SQLException, ClassNotFoundException;

    ArrayList<String> getReservationIds() throws SQLException, ClassNotFoundException;

    ArrayList<Reservation> getAllReservationsById(String Id)throws ClassNotFoundException, SQLException;

    ArrayList<Reservation> getAllStudent() throws SQLException, ClassNotFoundException;

    boolean deleteReservation(String code) throws SQLException, ClassNotFoundException;

    boolean saveReservation(Reservation dto) throws SQLException, ClassNotFoundException;

    boolean updateReservation(Reservation dto) throws SQLException, ClassNotFoundException;

    boolean existsReservation(String code) throws SQLException, ClassNotFoundException;

    Reservation searchReservation(String code) throws SQLException, ClassNotFoundException;

    String generateNewReservationId() throws SQLException, ClassNotFoundException;

    ArrayList<String> searchReservationCode() throws SQLException, ClassNotFoundException;

    ArrayList<Student> getAllStudentsById(String Id)throws ClassNotFoundException, SQLException;

    ArrayList<String> getStudentIds() throws SQLException, ClassNotFoundException;

    ArrayList<String> getRoomIds() throws SQLException, ClassNotFoundException;

    ArrayList<Rooms> getAllRoomsById(String Id)throws ClassNotFoundException, SQLException;

    int searchRoomQty(String code,String name) throws Exception;

    int updateRoomQty(String code,String name) throws Exception;
}
