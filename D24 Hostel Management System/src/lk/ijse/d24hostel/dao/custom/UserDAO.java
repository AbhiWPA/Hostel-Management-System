package lk.ijse.d24hostel.dao.custom;

import lk.ijse.d24hostel.dao.CrudDAO;
import lk.ijse.d24hostel.dto.UserDTO;
import lk.ijse.d24hostel.entity.User;

import java.sql.SQLException;
import java.util.ArrayList;

public interface UserDAO extends CrudDAO<User,String> {
    ArrayList<UserDAO> getAllUsersById(String Id)throws ClassNotFoundException, SQLException;

    String getUserIdByUserName(String name) throws ClassNotFoundException, SQLException;

    String getUserIdByPassword(String pswd) throws ClassNotFoundException, SQLException;
}
