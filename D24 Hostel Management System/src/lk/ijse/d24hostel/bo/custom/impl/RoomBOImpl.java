package lk.ijse.d24hostel.bo.custom.impl;

import lk.ijse.d24hostel.bo.custom.RoomsBO;
import lk.ijse.d24hostel.dao.custom.RoomsDAO;
import lk.ijse.d24hostel.dao.custom.impl.RoomsDAOImpl;
import lk.ijse.d24hostel.dto.RoomsDTO;
import lk.ijse.d24hostel.entity.Rooms;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.sql.SQLException;
import java.util.ArrayList;

public class RoomBOImpl implements RoomsBO {
    RoomsDAO roomsDAO = new RoomsDAOImpl();

    @Override
    public ArrayList<Rooms> getAllRooms() throws SQLException, ClassNotFoundException {
        return roomsDAO.getAll();
    }

    @Override
    public ArrayList<String> getAllRoomIds() throws SQLException, ClassNotFoundException {
        return roomsDAO.getIds();
    }

    @Override
    public ArrayList<Rooms> getAllRoomsByIds(String id) throws SQLException, ClassNotFoundException {
        return roomsDAO.getAllRoomsById(id);
    }

    @Override
    public boolean deleteRoom(String code) throws SQLException, ClassNotFoundException {
        return roomsDAO.delete(code);
    }

    @Override
    public boolean saveRoom(Rooms dto) throws SQLException, ClassNotFoundException {
        return roomsDAO.save(dto);
    }

    @Override
    public boolean updateRoom(Rooms dto) throws SQLException, ClassNotFoundException {
        return roomsDAO.update(dto);
    }

    @Override
    public boolean existsRoom(String code) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public Rooms searchRoom(String code) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public String generateNewRoomId() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public ArrayList<String> searchRoomCode() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public ArrayList<Integer> getRoomsQtyById(String Id) throws ClassNotFoundException, SQLException {
        return roomsDAO.getRoomsQtyById(Id);
    }
}
