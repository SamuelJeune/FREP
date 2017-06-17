package com.permispiste.service;

import com.permispiste.metier.ActionEntity;

import java.util.List;

public class ServiceAction extends Services {
    public List<ActionEntity> getAll() {
        List<ActionEntity> actions;
        String request = "SELECT action FROM ActionEntity action";
        actions = this.execute(request, ActionEntity.class);
        return actions;
    }

    public ActionEntity getById(int id) {
        ActionEntity action;
        String request = "SELECT action FROM ActionEntity action WHERE action.numaction = " + id;
        action = this.execute(request, ActionEntity.class).get(0);
        return action;
    }

    public void saveOrUpdate(ActionEntity action) {
        super.saveOrUpdate(action);
    }

    public void remove(int id) {
        this.remove(this.getById(id));
    }

    public void remove(ActionEntity action) {
        super.remove(action);
    }

    public List<ActionEntity> getFilles(int id) {
        List<ActionEntity> actionsFilles;
        String request = "SELECT action FROM ActionEntity action WHERE action.actNumaction = " + id;
        actionsFilles = this.execute(request, ActionEntity.class);
        return actionsFilles;
    }
}
