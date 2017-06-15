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

    public List<InscriptionEntity> getByJeu(int id) {
        List<InscriptionEntity> inscriptionsForJeu;
        String request = "SELECT inscription FROM InscriptionEntity inscription WHERE inscription.numjeu = " + id;
        inscriptionsForJeu = this.execute(request, InscriptionEntity.class);
        return inscriptionsForJeu;
    }

    public void saveOrUpdate(InscriptionEntity inscription) {
        super.saveOrUpdate(inscription);
    }

    public void remove(InscriptionEntity inscription) {
        super.remove(inscription);
    }

    public void remove(int idA, int idJ) {
        List<InscriptionEntity> inscriptionsToRemove;
        String request = "SELECT inscription FROM InscriptionEntity inscription WHERE inscription.numapprenant = " + idA + " AND inscription.numjeu = " + idJ;
        inscriptionsToRemove = this.execute(request, InscriptionEntity.class);
        inscriptionsToRemove.forEach(this::remove);
    }
}
