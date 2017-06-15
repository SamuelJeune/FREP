package com.permispiste.service;

import com.permispiste.metier.MissionEntity;

import java.util.List;

public class ServiceMission extends Services{

    public List<MissionEntity> getAll() {
        List<MissionEntity> missions;
        String request = "SELECT mission FROM MissionEntity mission";
        missions = this.execute(request, MissionEntity.class);
        return missions;
    }

    public List<MissionEntity> getByJeu(int id) {
        List<MissionEntity> missionsForJeu;
        String request = "SELECT mission FROM MissionEntity mission WHERE mission.numjeu = " + id;
        missionsForJeu = this.execute(request, MissionEntity.class);
        return missionsForJeu;
    }

    public MissionEntity getById(int id) {
        MissionEntity mission;
        String request = "SELECT mission FROM MissionEntity mission WHERE mission.nummission = " + id;
        mission = this.execute(request, MissionEntity.class).get(0);
        return mission;
    }

    public void remove(MissionEntity mission) {
        super.remove(mission);
    }

    public void remove(int id) {
        this.remove(this.getById(id));
    }

    public void saveOrUpdate(MissionEntity mission) {
        super.saveOrUpdate(mission);
    }
}
