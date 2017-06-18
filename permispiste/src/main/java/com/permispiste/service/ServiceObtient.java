package com.permispiste.service;

import com.permispiste.metier.ObtientEntity;

import java.util.List;

public class ServiceObtient extends Services {
    public List<ObtientEntity> getByAction(int id) {
        List<ObtientEntity> obtients;
        String request = "SELECT obtient FROM ObtientEntity obtient WHERE obtient.numaction = " + id;
        obtients = this.execute(request, ObtientEntity.class);
        return obtients;
    }

    public List<ObtientEntity> getByApprenant(int id) {
        List<ObtientEntity> obtients;
        String request = "SELECT obtient FROM ObtientEntity obtient WHERE obtient.numapprenant = " + id;
        obtients = this.execute(request, ObtientEntity.class);
        return obtients;
    }

    public void saveOrUpdate(ObtientEntity obtient) {
        super.saveOrUpdate(obtient);
    }

    public void remove(ObtientEntity obtient) {
        super.remove(obtient);
    }

    public void remove(int idAc, int idAp) {
        List<ObtientEntity> obtientsToRemove;
        String request = "SELECT obtient FROM ObtientEntity obtient WHERE obtient.numaction = " + idAc + " AND obtient.numapprenant = " + idAp;
        obtientsToRemove = this.execute(request, ObtientEntity.class);
        obtientsToRemove.forEach(this::remove);
    }
}
