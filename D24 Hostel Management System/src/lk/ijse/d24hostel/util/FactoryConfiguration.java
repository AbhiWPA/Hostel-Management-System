package lk.ijse.d24hostel.util;

import lk.ijse.d24hostel.entity.Reservation;
import lk.ijse.d24hostel.entity.Rooms;
import lk.ijse.d24hostel.entity.Student;
import lk.ijse.d24hostel.entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import java.io.IOException;
import java.util.Properties;

public class FactoryConfiguration {
    private static FactoryConfiguration factoryConfiguration;
    private SessionFactory sessionFactory;

    private FactoryConfiguration() {
        Configuration configure = new Configuration()
                .addAnnotatedClass(Student.class)
                .addAnnotatedClass(Rooms.class)
                .addAnnotatedClass(Reservation.class)
                .addAnnotatedClass(User.class);
        sessionFactory = configure.buildSessionFactory();
    }

    public static FactoryConfiguration getInstance(){
        return factoryConfiguration == null ? factoryConfiguration = new FactoryConfiguration() :
                factoryConfiguration;
    }

    public Session getSessionFactory(){
        return sessionFactory.openSession();
    }
}
