package com.permispiste.service;

import com.permispiste.metier.InscriptionEntity;

import java.util.List;

public class ServiceInscription extends Services {

    public List<InscriptionEntity> getByApprenant(int id) {
        List<InscriptionEntity> inscriptionsForApprenant;
        String request = "SELECT inscription FROM InscriptionEntity inscription WHERE inscription.numapprenant = " + id;
        inscriptionsForApprenant = this.execute(request, InscriptionEntity.class);
        return inscriptionsForApprenant;
    }

    public void saveOrUpdate(InscriptionEntity inscription) {
        super.saveOrUpdate(inscription);
    }
}
