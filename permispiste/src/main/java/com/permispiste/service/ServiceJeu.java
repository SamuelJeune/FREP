package com.permispiste.service;

import com.permispiste.metier.JeuEntity;

import java.util.List;

public class ServiceJeu extends Services {
    public List<JeuEntity> getAll() {
        List<JeuEntity> jeux;
        String request = "SELECT jeu FROM JeuEntity jeu";
        jeux = this.execute(request, JeuEntity.class);
        return jeux;
    }

    public JeuEntity getById(int id) {
        JeuEntity jeu;
        String request = "SELECT jeu FROM JeuEntity jeu WHERE jeu.numjeu = " + id;
        jeu = this.execute(request, JeuEntity.class).get(0);
        return jeu;
    }

    public void saveOrUpdate(JeuEntity jeu) {
        super.saveOrUpdate(jeu);
    }

    public void remove(int id) {
        this.remove(this.getById(id));
    }

    public void remove(JeuEntity jeu) {
        super.remove(jeu);
    }
}
