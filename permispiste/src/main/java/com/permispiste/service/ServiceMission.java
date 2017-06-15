package com.permispiste.service;

import com.permispiste.metier.MissionEntity;

import java.util.List;

/**
 * Created by Robin on 14/06/2017.
 */
public class ServiceMission extends Services{

    public List<MissionEntity> getByJeu(int id) {
        List<MissionEntity> missionsForJeu;
        String request = "SELECT mission FROM MissionEntity mission WHERE mission.numjeu = " + id;
        missionsForJeu = this.execute(request, MissionEntity.class);
        return missionsForJeu;
    }
}
