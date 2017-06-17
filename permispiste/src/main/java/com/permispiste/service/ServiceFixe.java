package com.permispiste.service;

import com.permispiste.metier.FixeEntity;

import java.util.List;

public class ServiceFixe extends Services {
    public List<FixeEntity> getByMission(int id) {
        List<FixeEntity> fixes;
        String request = "SELECT fixe FROM FixeEntity fixe WHERE fixe.nummission = " + id;
        fixes = this.execute(request, FixeEntity.class);
        return fixes;
    }

    public List<FixeEntity> getByObjectif(int id) {
        List<FixeEntity> fixes;
        String request = "SELECT fixe FROM FixeEntity fixe WHERE fixe.numobjectif = " + id;
        fixes = this.execute(request, FixeEntity.class);
        return fixes;
    }

    public void saveOrUpdate(FixeEntity fixe) {
        super.saveOrUpdate(fixe);
    }

    public void remove(FixeEntity fixe) {
        super.remove(fixe);
    }

    public void remove(int idM, int idO) {
        List<FixeEntity> fixesToRemove;
        String request = "SELECT fixe FROM FixeEntity fixe WHERE fixe.nummission = " + idM + " AND fixe.numobjectif = " + idO;
        fixesToRemove = this.execute(request, FixeEntity.class);
        fixesToRemove.forEach(this::remove);
    }
}
