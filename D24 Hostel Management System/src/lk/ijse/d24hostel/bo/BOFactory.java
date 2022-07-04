package lk.ijse.d24hostel.bo;

import lk.ijse.d24hostel.bo.custom.impl.ReservationBOImpl;
import lk.ijse.d24hostel.bo.custom.impl.RoomBOImpl;
import lk.ijse.d24hostel.bo.custom.impl.StudentBOImpl;
import lk.ijse.d24hostel.dao.custom.impl.StudentDAOImpl;

public class BOFactory {
    private static BOFactory boFactory;

    private BOFactory() {
    }

    public static BOFactory getBoFactory(){
        if (boFactory==null) {
            boFactory=new BOFactory();
        }
        return boFactory;
    }

    public enum BOTypes{
        STUDENT, ROOM, RESERVATION
    }

    public SuperBO getBO(BOTypes type){
        switch (type){
            case STUDENT:
                return new StudentBOImpl();
            case ROOM:
                return new RoomBOImpl();
            case RESERVATION:
                return new ReservationBOImpl();
            default:
                return null;
        }
    }
}
