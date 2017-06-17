package com.permispiste.service;

import com.permispiste.metier.EstAssocieEntity;

import java.util.List;

public class ServiceEstAssocie extends Services {
    public List<EstAssocieEntity> getByAction(int id) {
        List<EstAssocieEntity> estAssocies;
        String request = "SELECT estAssocie FROM EstAssocieEntity estAssocie WHERE estAssocie.numaction = " + id;
        estAssocies = this.execute(request, EstAssocieEntity.class);
        return estAssocies;
    }

    public List<EstAssocieEntity> getByObjectif(int id) {
        List<EstAssocieEntity> estAssocies;
        String request = "SELECT estAssocie FROM EstAssocieEntity estAssocie WHERE estAssocie.numobjectif = " + id;
        estAssocies = this.execute(request, EstAssocieEntity.class);
        return estAssocies;
    }

    public void saveOrUpdate(EstAssocieEntity estAssocie) {
        super.saveOrUpdate(estAssocie);
    }

    public void remove(EstAssocieEntity estAssocie) {
        super.remove(estAssocie);
    }

    public void remove(int idA, int idO) {
        List<EstAssocieEntity> estAssociesToRemove;
        String request = "SELECT estAssocie FROM EstAssocieEntity estAssocie WHERE estAssocie.numaction = " + idA + " AND estAssocie.numobjectif = " + idO;
        estAssociesToRemove = this.execute(request, EstAssocieEntity.class);
        estAssociesToRemove.forEach(this::remove);
    }
}
