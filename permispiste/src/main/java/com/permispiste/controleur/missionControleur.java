package com.permispiste.controleur;

import com.permispiste.metier.FixeEntity;
import com.permispiste.metier.JeuEntity;
import com.permispiste.metier.MissionEntity;
import com.permispiste.metier.ObjectifEntity;
import com.permispiste.service.ServiceFixe;
import com.permispiste.service.ServiceJeu;
import com.permispiste.service.ServiceMission;
import com.permispiste.service.ServiceObjectif;
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
import java.util.stream.Collectors;

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

        ServiceJeu SJ = new ServiceJeu();
        List<JeuEntity> jeux = SJ.getAll();
        model.addAttribute("jeux", jeux);

        return "Forms/FormMission";
    }

    @RequestMapping(value = "{id}/supprimer", method = RequestMethod.POST)
    public String supprimerMission(@PathVariable("id") int id, RedirectAttributes redirectAttributes) {
        ServiceFixe SF = new ServiceFixe();
        SF.getByMission(id).forEach(SF::remove);

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

        ServiceFixe SF = new ServiceFixe();
        List<FixeEntity> fixeForMission = SF.getByMission(id);
        ServiceObjectif SO = new ServiceObjectif();
        List<ObjectifEntity> objectifForMission = fixeForMission.stream().map(f -> SO.getById(f.getNumobjectif())).collect(Collectors.toList());
        model.addAttribute("objectifs", objectifForMission);

        return "Missions/afficheMission";
    }

    @RequestMapping(value = "{id}/fixer-objectif", method = RequestMethod.GET)
    public String ajouterObjectif(@PathVariable("id") int id, Model model) {
        List<MissionEntity> missions = SM.getAll();
        model.addAttribute("missions", missions);

        ServiceObjectif SO = new ServiceObjectif();
        List<ObjectifEntity> objectifs = SO.getAll();
        model.addAttribute("objectifs", objectifs);

        FixeEntity fixe = new FixeEntity();
        MissionEntity mission = SM.getById(id);
        fixe.setNummission(mission.getNummission());
        model.addAttribute("fixe", fixe);

        return "Forms/FormFixer";
    }

    @RequestMapping(value = "fixer", method = RequestMethod.POST)
    public String fixerObjectif(@ModelAttribute @Validated FixeEntity fixe, BindingResult result, RedirectAttributes redirectAttributes) {
        if(result.hasErrors()) {
            return "Forms/FormFixer";
        }
        else {
            ServiceFixe SF = new ServiceFixe();
            SF.saveOrUpdate(fixe);

            redirectAttributes.addFlashAttribute("css", "success");
            redirectAttributes.addFlashAttribute("msg", "Objectif fixé avec succès.");

            return "redirect:/missions/" + fixe.getNummission();
        }
    }

    @RequestMapping(value = "{idM}/retirer-objectif/{idO}", method = RequestMethod.POST)
    public String retirerObjectif(@PathVariable("idM") int idM, @PathVariable("idO") int idO, RedirectAttributes redirectAttributes) {
        ServiceFixe SF = new ServiceFixe();
        SF.remove(idM, idO);

        redirectAttributes.addFlashAttribute("css", "success");
        redirectAttributes.addFlashAttribute("msg", "Objectif retiré avec succès.");

        return "redirect:/missions/" + idM;
    }
}
