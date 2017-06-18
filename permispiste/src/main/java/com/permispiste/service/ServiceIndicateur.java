package com.permispiste.service;

import com.permispiste.metier.IndicateurEntity;

import java.util.List;

public class ServiceIndicateur extends Services {
    public List<IndicateurEntity> getAll() {
        List<IndicateurEntity> indicateurs;
        String request = "SELECT indicateur FROM IndicateurEntity indicateur";
        indicateurs = this.execute(request, IndicateurEntity.class);
        return indicateurs;
    }

    public IndicateurEntity getById(int id) {
        IndicateurEntity indicateur;
        String request = "SELECT indicateur FROM IndicateurEntity indicateur WHERE indicateur.numindicateur = " + id;
        indicateur = this.execute(request, IndicateurEntity.class).get(0);
        return indicateur;
    }

    public IndicateurEntity getByAction(int id) {
        IndicateurEntity indicateur;
        String request = "SELECT indicateur FROM IndicateurEntity indicateur WHERE indicateur.numaction = " + id;
        List<IndicateurEntity> result = this.execute(request, IndicateurEntity.class);
        if(result.isEmpty()) return null;
        else return result.get(0);
    }

    public void saveOrUpdate(IndicateurEntity indicateur) {
        super.saveOrUpdate(indicateur);
    }

    public void remove(int id) {
        this.remove(this.getById(id));
    }

    public void remove(IndicateurEntity indicateur) {
        super.remove(indicateur);
    }
}
