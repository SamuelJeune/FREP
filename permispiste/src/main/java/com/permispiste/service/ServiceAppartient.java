package com.permispiste.service;

import com.permispiste.metier.AppartientEntity;

import java.util.List;

public class ServiceAppartient extends Services {
    public List<AppartientEntity> getAll() {
        List<AppartientEntity> appartients;
        String request = "SELECT appartient FROM AppartientEntity appartient";
        appartients = this.execute(request, AppartientEntity.class);
        return appartients;
    }

    public List<AppartientEntity> getByAction(int id) {
        List<AppartientEntity> appartients;
        String request = "SELECT appartient FROM AppartientEntity appartient WHERE appartient.numaction = " + id;
        appartients = this.execute(request, AppartientEntity.class);
        return appartients;
    }

    public List<AppartientEntity> getByJeu(int id) {
        List<AppartientEntity> appartients;
        String request = "SELECT appartient FROM AppartientEntity appartient WHERE appartient.numjeu = " + id;
        appartients = this.execute(request, AppartientEntity.class);
        return appartients;
    }

    public void saveOrUpdate(AppartientEntity appartient) {
        super.saveOrUpdate(appartient);
    }

    public void remove(int idJ, int idA) {
        List<AppartientEntity> appartientsToRemove;
        String request = "SELECT appartient FROM AppartientEntity appartient WHERE appartient.numjeu = " + idJ + " AND appartient.numaction = " + idA;
        appartientsToRemove = this.execute(request, AppartientEntity.class);
        appartientsToRemove.forEach(this::remove);
    }

    public void remove(AppartientEntity appartient) {
        super.remove(appartient);
    }
}
