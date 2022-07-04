package lk.ijse.d24hostel.bo.custom.impl;

import lk.ijse.d24hostel.bo.custom.UserBO;
import lk.ijse.d24hostel.dao.custom.UserDAO;
import lk.ijse.d24hostel.dao.custom.impl.UserDAOImpl;
import lk.ijse.d24hostel.entity.Student;
import lk.ijse.d24hostel.entity.User;

import java.sql.SQLException;
import java.util.ArrayList;

public class UserBOImpl implements UserBO {
    UserDAO userDAO = new UserDAOImpl();
    @Override
    public ArrayList<User> getAllUsers() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public String getUserIdByUserName(String name) throws ClassNotFoundException, SQLException {
        return userDAO.getUserIdByUserName(name);
    }

    @Override
    public String getUserIdByPassword(String pswd) throws ClassNotFoundException, SQLException {
        return userDAO.getUserIdByPassword(pswd);
    }

    @Override
    public ArrayList<User> getAllUsersById(String id) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public ArrayList<String> getAllUserIds() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean deleteUser(String code) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean saveUser(User dto) throws SQLException, ClassNotFoundException {
        return userDAO.save(dto);
    }

    @Override
    public boolean updateUser(User dto) throws SQLException, ClassNotFoundException {
        return userDAO.update(dto);
    }

    @Override
    public boolean existsUser(String code) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public User searchUser(String code) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public String generateNewUserId() throws SQLException, ClassNotFoundException {
        return userDAO.generateNewID();
    }

    @Override
    public ArrayList<String> searchUserCode() throws SQLException, ClassNotFoundException {
        return null;
    }
}
