package com.permispiste.DAO;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Created by Robin on 12/06/2017.
 */
public class EntityManagerService {
    private static EntityManagerFactory EMF = Persistence.createEntityManagerFactory("permispiste");
    protected static EntityManager em = EMF.createEntityManager();

}
