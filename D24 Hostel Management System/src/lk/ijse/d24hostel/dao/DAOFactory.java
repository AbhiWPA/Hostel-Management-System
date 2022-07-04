package lk.ijse.d24hostel.dao;

import lk.ijse.d24hostel.dao.custom.impl.ReservationDAOImpl;
import lk.ijse.d24hostel.dao.custom.impl.RoomsDAOImpl;
import lk.ijse.d24hostel.dao.custom.impl.StudentDAOImpl;
import lk.ijse.d24hostel.dao.custom.impl.UserDAOImpl;

public class DAOFactory {
    private static DAOFactory daoFactory;

    private DAOFactory(){
    }

    public static DAOFactory getDaoFactory(){
        if (daoFactory==null){
            daoFactory = new DAOFactory();
        }
        return daoFactory;
    }

    public enum DAOTypes{
        STUDENT, ROOMS, USER, RESERVATION
    }

    public SuperDAO getDAO(DAOTypes types){
        switch (types){
            case STUDENT:
                return new StudentDAOImpl();

            case ROOMS:
                return new RoomsDAOImpl();

            case USER:
                new UserDAOImpl();

            case RESERVATION:
                return new ReservationDAOImpl();

            default:
                return null;
        }
    }
}
