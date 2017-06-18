package com.permispiste.service;

import com.permispiste.metier.ApprenantEntity;

import java.util.List;

public class ServiceApprenant extends Services {

    public List<ApprenantEntity> getAll() {
        List<ApprenantEntity> apprenants;
        String request = "SELECT apprenant FROM ApprenantEntity apprenant";
        apprenants = this.execute(request, ApprenantEntity.class);
        return apprenants;
    }

    public ApprenantEntity getById(int id) {
        ApprenantEntity apprenant;
        String request = "SELECT apprenant FROM ApprenantEntity apprenant WHERE apprenant.numapprenant = " + id;
        apprenant = this.execute(request, ApprenantEntity.class).get(0);
        return apprenant;
    }

    public void saveOrUpdate(ApprenantEntity apprenant) {
        super.saveOrUpdate(apprenant);
    }

    public void remove(ApprenantEntity apprenant) {
        super.remove(apprenant);
    }

    public void remove(int id) {
        this.remove(this.getById(id));
    }

    public List<ApprenantEntity> getByName(String recherche) {
        List<ApprenantEntity> apprenants;
        String request = "SELECT apprenant FROM ApprenantEntity apprenant WHERE concat(apprenant.prenomapprenant, ' ', apprenant.nomapprenant) LIKE '%" + recherche + "%' OR concat(apprenant.nomapprenant, ' ', apprenant.prenomapprenant) LIKE '%" + recherche + "%'";
        apprenants = this.execute(request, ApprenantEntity.class);
        return apprenants;
    }
}
