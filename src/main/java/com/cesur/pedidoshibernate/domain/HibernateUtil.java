package com.cesur.pedidoshibernate.domain;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * La clase HibernateUtil proporciona métodos para obtener la factoría de sesiones de Hibernate.
 * Esta clase utiliza la configuración de Hibernate para construir la factoría de sesiones.
 */
public class HibernateUtil {
    // Factoría de sesiones de Hibernate
    private static SessionFactory sf = null;

    static {
        try {
            Configuration cfg = new Configuration();
            cfg.configure();
            sf = cfg.buildSessionFactory();
            System.out.println("SESSION FACTORY CREADO CON EXITO");

        } catch (Exception e) {
            System.out.println("ERROR: " + e.getMessage());
        }

    }

    /**
     * Obtiene la factoría de sesiones de Hibernate.
     *
     * @return La factoría de sesiones de Hibernate.
     */
    public static SessionFactory getSessionFactory() {
        return sf;
    }
}
