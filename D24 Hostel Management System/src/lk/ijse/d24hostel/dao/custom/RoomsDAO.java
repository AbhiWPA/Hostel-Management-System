package lk.ijse.d24hostel.dao.custom;

import lk.ijse.d24hostel.dao.CrudDAO;
import lk.ijse.d24hostel.dto.RoomsDTO;
import lk.ijse.d24hostel.entity.Rooms;

import java.sql.SQLException;
import java.util.ArrayList;

public interface RoomsDAO extends CrudDAO<Rooms,String> {
    ArrayList<Rooms> getAllRoomsById(String Id)throws ClassNotFoundException, SQLException;

    ArrayList<Integer> getRoomsQtyById(String Id)throws ClassNotFoundException, SQLException;

}
