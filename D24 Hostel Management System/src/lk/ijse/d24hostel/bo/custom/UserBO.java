package lk.ijse.d24hostel.bo.custom;

import lk.ijse.d24hostel.bo.SuperBO;
import lk.ijse.d24hostel.entity.Student;
import lk.ijse.d24hostel.entity.User;

import javax.jws.soap.SOAPBinding;
import java.sql.SQLException;
import java.util.ArrayList;

public interface UserBO extends SuperBO {

    ArrayList<User> getAllUsers() throws SQLException, ClassNotFoundException;

    String getUserIdByUserName(String name) throws ClassNotFoundException, SQLException;

    String getUserIdByPassword(String pswd) throws ClassNotFoundException, SQLException;

    ArrayList<User> getAllUsersById(String id) throws SQLException, ClassNotFoundException;

    ArrayList<String> getAllUserIds() throws SQLException, ClassNotFoundException;

    boolean deleteUser(String code) throws SQLException, ClassNotFoundException;

    boolean saveUser(User dto) throws SQLException, ClassNotFoundException;

    boolean updateUser(User dto) throws SQLException, ClassNotFoundException;

    boolean existsUser(String code) throws SQLException, ClassNotFoundException;

    User searchUser(String code) throws SQLException, ClassNotFoundException;

    String generateNewUserId() throws SQLException, ClassNotFoundException;

    ArrayList<String> searchUserCode() throws SQLException, ClassNotFoundException;

}
