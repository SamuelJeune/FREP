package com.permispiste.service;

import com.permispiste.DAO.HibernateSession;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.TypedQuery;
import java.util.List;

abstract class Services {
    List execute(String request) {
        return execute(request, Object.class);
    }

    <T> List<T> execute(String request, Class<T> type) {
        List<T> result;
        Session session = HibernateSession.currentSession();
        TypedQuery<T> query = session.createQuery(request, type);
        result = query.getResultList();
        HibernateSession.closeSession();
        return result;
    }

    void saveOrUpdate(Object o) {
        Session session = HibernateSession.currentSession();
        Transaction transaction = session.beginTransaction();
        session.saveOrUpdate(o);
        transaction.commit();
        HibernateSession.closeSession();
    }

    void remove(Object o) {
        Session session = HibernateSession.currentSession();
        Transaction transaction = session.beginTransaction();
        session.remove(o);
        transaction.commit();
        HibernateSession.closeSession();
    }
}
