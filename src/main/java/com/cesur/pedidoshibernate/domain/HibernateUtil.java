package com.cesur.pedidoshibernate.domain;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {

    private static SessionFactory sf = null;

    static {
        try {
            Configuration cfg = new Configuration();
            cfg.configure();
            sf = cfg.buildSessionFactory();

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

    }

    public static SessionFactory getSessionFactory() {
        return sf;
    }
}
