package com.permispiste.controleur;

import com.permispiste.metier.JeuEntity;
import com.permispiste.metier.MissionEntity;
import com.permispiste.service.ServiceJeu;
import com.permispiste.service.ServiceMission;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("missions")
public class missionControleur {
    private static ServiceMission SM = new ServiceMission();

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String afficheMissions(Model model) {
        List<MissionEntity> missions = SM.getAll();
        model.addAttribute("missions", missions);
        return "Missions/missions";
    }

    @RequestMapping(value = "ajouter", method = RequestMethod.GET)
    public String ajouterMission(Model model) {
        MissionEntity mission = new MissionEntity();
        model.addAttribute("mission", mission);

        ServiceJeu SJ = new ServiceJeu();
        List<JeuEntity> jeux = SJ.getAll();
        model.addAttribute("jeux", jeux);

        return "Forms/FormMission";
    }

    @RequestMapping(value = "creer", method = RequestMethod.POST)
    public String insererMission(@ModelAttribute("mission") @Validated MissionEntity mission, BindingResult result, RedirectAttributes redirectAttributes) {
        if(result.hasErrors()) {
            return "Forms/FormMission";
        }
        else {
            SM.saveOrUpdate(mission);
            redirectAttributes.addFlashAttribute("css", "success");
            redirectAttributes.addFlashAttribute("msg", "Mission enregistrée avec succès.");
            return "redirect:/missions/" + mission.getNummission();
        }
    }

    @RequestMapping(value = "{id}/modifier", method = RequestMethod.GET)
    public String modifierMission(@PathVariable("id") int id, Model model) {
        MissionEntity mission = SM.getById(id);
        model.addAttribute("mission", mission);

        return "Forms/FormMission";
    }

    @RequestMapping(value = "{id}/supprimer", method = RequestMethod.POST)
    public String supprimerMission(@PathVariable("id") int id, RedirectAttributes redirectAttributes) {
        SM.remove(id);

        redirectAttributes.addFlashAttribute("css", "success");
        redirectAttributes.addFlashAttribute("msg", "Mission supprimée avec succès.");

        return "redirect:/missions";
    }

    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public String afficheMission(@PathVariable("id") int id, Model model) {
        MissionEntity mission = SM.getById(id);
        model.addAttribute("mission", mission);

        ServiceJeu SJ = new ServiceJeu();
        JeuEntity jeuForMission = SJ.getById(mission.getNumjeu());
        model.addAttribute("jeu", jeuForMission);

        return "Missions/afficheMission";
    }
}
