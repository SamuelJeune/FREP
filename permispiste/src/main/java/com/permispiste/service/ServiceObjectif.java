package com.permispiste.service;

import com.permispiste.metier.ObjectifEntity;

import java.util.List;

public class ServiceObjectif extends Services {
    public List<ObjectifEntity> getAll() {
        List<ObjectifEntity> objectifs;
        String request = "SELECT objectif FROM ObjectifEntity objectif";
        objectifs = this.execute(request, ObjectifEntity.class);
        return objectifs;
    }

    public ObjectifEntity getById(int id) {
        ObjectifEntity objectif;
        String request = "SELECT objectif FROM ObjectifEntity objectif WHERE objectif.numobjectif = " + id;
        objectif = this.execute(request, ObjectifEntity.class).get(0);
        return objectif;
    }

    public void saveOrUpdate(ObjectifEntity objectif) {
        super.saveOrUpdate(objectif);
    }

    public void remove(int id) {
        this.remove(this.getById(id));
    }

    public void remove(ObjectifEntity objectif) {
        super.remove(objectif);
    }
}
