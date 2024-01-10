package org.example.Hibernate;

import org.example.domain.EstudiantesClass;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;


public class EstudiantesHibernate {

    private static final SessionFactory sessionFactory;

    static {
        try {
            // Crea una sesión de Hibernate
            sessionFactory = new Configuration().configure().buildSessionFactory();
        } catch (Throwable ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }

    public int insert(EstudiantesClass estudiante) {
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        int rows = 0;

        try {
            tx = session.beginTransaction();
            session.save(estudiante); // Guarda el estudiante en la base de datos
            tx.commit();
            rows = 1;
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }

        return rows;
    }

    public static void shutdown() {
        sessionFactory.close();
    }
}
