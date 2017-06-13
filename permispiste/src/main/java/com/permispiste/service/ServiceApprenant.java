package com.permispiste.service;

import com.permispiste.DAO.HibernateSession;
import com.permispiste.metier.ApprenantEntity;
import org.hibernate.Session;

import javax.persistence.TypedQuery;
import java.util.List;

public class ServiceApprenant {

    public List<ApprenantEntity> getAll() {
        List<ApprenantEntity> apprenants;
        String request = "SELECT apprenant FROM ApprenantEntity apprenant";
        Session session = HibernateSession.currentSession();
        TypedQuery<ApprenantEntity> query = session.createQuery(request, ApprenantEntity.class);
        apprenants = query.getResultList();
        HibernateSession.closeSession();
        return apprenants;
    }
}
