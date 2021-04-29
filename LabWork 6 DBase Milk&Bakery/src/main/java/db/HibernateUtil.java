package db;

import model.Product;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import java.io.File;

public class HibernateUtil {
    private static SessionFactory sessionFactory;

    static {
        File cfgFile = new File("hibernate.cfg.xml");
        sessionFactory = new Configuration().configure(cfgFile)
                .addAnnotatedClass(Nomenclature.class).addAnnotatedClass(WareHouse.class).buildSessionFactory();
    }

    public static Session getSession() {
        return sessionFactory.openSession();
    }
}
