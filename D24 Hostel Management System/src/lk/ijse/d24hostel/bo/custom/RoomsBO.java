package lk.ijse.d24hostel.bo.custom;

import lk.ijse.d24hostel.bo.SuperBO;
import lk.ijse.d24hostel.dto.RoomsDTO;
import lk.ijse.d24hostel.dto.StudentDTO;
import lk.ijse.d24hostel.entity.Rooms;

import java.sql.SQLException;
import java.util.ArrayList;

public interface RoomsBO extends SuperBO {
    ArrayList<Rooms> getAllRooms() throws SQLException, ClassNotFoundException;

    ArrayList<String> getAllRoomIds() throws SQLException, ClassNotFoundException;

    ArrayList<Rooms> getAllRoomsByIds(String id) throws SQLException, ClassNotFoundException;

    boolean deleteRoom(String code) throws SQLException, ClassNotFoundException;

    boolean saveRoom(Rooms dto) throws SQLException, ClassNotFoundException;

    boolean updateRoom(Rooms dto) throws SQLException, ClassNotFoundException;

    boolean existsRoom(String code) throws SQLException, ClassNotFoundException;

    Rooms searchRoom(String code) throws SQLException, ClassNotFoundException;

    String generateNewRoomId() throws SQLException, ClassNotFoundException;

    ArrayList<String> searchRoomCode() throws SQLException, ClassNotFoundException;

    ArrayList<Integer> getRoomsQtyById(String Id)throws ClassNotFoundException, SQLException;
}
