package com.permispiste.service;

import com.permispiste.DAO.HibernateSession;
import com.permispiste.metier.ApprenantEntity;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

abstract class Services {
    List execute(String request) {
        Session session = HibernateSession.currentSession();
        Query query = session.createQuery(request);
        List resultList = query.getResultList();
        HibernateSession.closeSession();
        return resultList;
    }

    <T> List<T> execute(String request, Class<T> type) {
        Session session = HibernateSession.currentSession();
        TypedQuery<T> query = session.createQuery(request, type);
        List<T> resultList = query.getResultList();
        HibernateSession.closeSession();
        return resultList;
    }

    void saveOrUpdate(Object o) {
        Session session = HibernateSession.currentSession();
        Transaction transaction = session.beginTransaction();
        session.saveOrUpdate(o);
        transaction.commit();
        session.close();
    }

    void remove(Object o) {
        Session session = HibernateSession.currentSession();
        Transaction transaction = session.beginTransaction();
        session.remove(o);
        transaction.commit();
        session.close();
    }
}
