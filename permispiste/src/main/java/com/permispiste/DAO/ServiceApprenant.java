package com.permispiste.DAO;

import com.permispiste.metier.ApprenantEntity;
import org.hibernate.Session;

import javax.persistence.TypedQuery;
import java.util.List;

public class ServiceApprenant {

    public List<ApprenantEntity> getAll() {
        List<ApprenantEntity> apprenants;
        Session s = HibernateSession.currentSession();

        String request = "SELECT apprenant.id FROM ApprenantEntity AS apprenant";
        TypedQuery<ApprenantEntity> query = s.createQuery(request, ApprenantEntity.class);
        apprenants = query.getResultList();

        return apprenants;
    }
}
