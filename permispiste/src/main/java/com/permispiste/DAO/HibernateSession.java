package com.permispiste.DAO;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateSession {
    private static SessionFactory sessionFactory;
    private static ThreadLocal<Session> session;
    static {
        try {
            sessionFactory = new Configuration().configure().buildSessionFactory();
            session = new ThreadLocal<>();
        } catch(Exception e) {
            System.out.println("CACACACACACACACACACACACACACACACACACACACACACACAC");
            e.printStackTrace();
        }
    }

    public static Session currentSession() {
        Session s = session.get();
        if(s == null) {
            s = sessionFactory.openSession();
            session.set(s);
        }
        return s;
    }

    public static void closeSession() {
        Session s = session.get();
        session.set(null);
        if(s != null) {
            s.close();
        }
    }
}
