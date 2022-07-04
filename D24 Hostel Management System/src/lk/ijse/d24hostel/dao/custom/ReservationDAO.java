package lk.ijse.d24hostel.dao.custom;

import lk.ijse.d24hostel.dao.CrudDAO;
import lk.ijse.d24hostel.dto.ReservationDTO;
import lk.ijse.d24hostel.entity.Reservation;
import lk.ijse.d24hostel.entity.Rooms;
import lk.ijse.d24hostel.entity.Student;

import java.sql.SQLException;
import java.util.ArrayList;

public interface ReservationDAO extends CrudDAO<Reservation,String> {
    ArrayList<Reservation> getAllRoomReservationsById(String Id)throws ClassNotFoundException, SQLException;

    ArrayList<Student> getAllStudentsById(String Id)throws ClassNotFoundException, SQLException;

    ArrayList<String> getStudentIds() throws SQLException, ClassNotFoundException;

    ArrayList<String> getRoomIds() throws SQLException, ClassNotFoundException;

    ArrayList<Rooms> getAllRoomsById(String Id)throws ClassNotFoundException, SQLException;

    int searchRoomQty(String code,String name) throws Exception;

    int updateRoomQty(String code,String name) throws Exception;
}
