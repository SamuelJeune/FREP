package com.permispiste.controleur;

import com.permispiste.metier.*;
import com.permispiste.service.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
@RequestMapping("apprenants")
public class ApprenantControleur {
    private static ServiceApprenant SA = new ServiceApprenant();

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String afficheApprenants(Model model) {
        List<ApprenantEntity> apprenants = SA.getAll();
        model.addAttribute("apprenants", apprenants);
        return "Apprenants/apprenants";
    }

    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public String afficheApprenant(@PathVariable("id") int id, Model model) {
        ApprenantEntity apprenant = SA.getById(id);
        model.addAttribute("apprenant", apprenant);

        ServiceInscription SI = new ServiceInscription();
        ServiceJeu SJ = new ServiceJeu();
        ServiceMission SM = new ServiceMission();
        ServiceFixe SF = new ServiceFixe();
        ServiceObjectif SObj = new ServiceObjectif();
        ServiceEstAssocie SEA = new ServiceEstAssocie();
        ServiceAction SAc = new ServiceAction();
        ServiceObtient SObt = new ServiceObtient();
        Integer score;

        List<JeuEntity> jeuxForId = SI.getByApprenant(id).stream().map(i -> SJ.getById(i.getNumjeu())).collect(Collectors.toList());
        model.addAttribute("jeux", jeuxForId);

        Map<JeuEntity, List<MissionEntity>> missionsForId = new HashMap<>();
        for(JeuEntity jeu : jeuxForId) {
            missionsForId.put(jeu, SM.getByJeu(jeu.getNumjeu()));
        }
        model.addAttribute("missions", missionsForId);

        Map<MissionEntity, List<ObjectifEntity>> objectifsForId = new HashMap<>();
        for(JeuEntity jeu : jeuxForId) {
            for(MissionEntity mission : missionsForId.get(jeu)) {
                objectifsForId.put(mission, SF.getByMission(mission.getNummission()).stream().map(f -> SObj.getById(f.getNumobjectif())).collect(Collectors.toList()));
            }
        }
        model.addAttribute("objectifs", objectifsForId);

        Map<ObjectifEntity, List<ActionEntity>> actionsForId = new HashMap<>();
        for(JeuEntity jeu : jeuxForId) {
            for(MissionEntity mission : missionsForId.get(jeu)) {
                for(ObjectifEntity objectif : objectifsForId.get(mission)) {
                    actionsForId.put(objectif, SEA.getByObjectif(objectif.getNumobjectif()).stream().map(ea -> SAc.getById(ea.getNumaction())).collect(Collectors.toList()));
                }
            }
        }
        model.addAttribute("actions", actionsForId);

        Map<ActionEntity, ObtientEntity> obtientsForId = new HashMap<>();
        for(JeuEntity jeu : jeuxForId) {
            for(MissionEntity mission : missionsForId.get(jeu)) {
                for(ObjectifEntity objectif : objectifsForId.get(mission)) {
                    for(ActionEntity action : actionsForId.get(objectif)) {
                        obtientsForId.put(action, SObt.getByAction(action.getNumaction()).stream().filter(o -> o.getNumapprenant() == id).findFirst().orElse(null));
                    }
                }
            }
        }
        model.addAttribute("obtients", obtientsForId);

        Map<ObjectifEntity, Integer> scoreForObjectifs = new HashMap<>();
        ObtientEntity obtenu;
        for(JeuEntity jeu : jeuxForId) {
            for(MissionEntity mission : missionsForId.get(jeu)) {
                for(ObjectifEntity objectif : objectifsForId.get(mission)) {
                    score = 0;
                    for(ActionEntity action : actionsForId.get(objectif)) {
                        obtenu = obtientsForId.get(action);
                        if(obtenu != null && obtenu.getValeur() >= action.getScoremin()) {
                            score++;
                        }
                    }
                    scoreForObjectifs.put(objectif, score);
                }
            }
        }
        model.addAttribute("scoreForObjectifs", scoreForObjectifs);

        Map<MissionEntity, Integer> scoreForMissions = new HashMap<>();
        for(JeuEntity jeu : jeuxForId) {
            for(MissionEntity mission : missionsForId.get(jeu)) {
                score = 0;
                for(ObjectifEntity objectif : objectifsForId.get(mission)) {
                    if(scoreForObjectifs.get(objectif) == actionsForId.get(objectif).size()) {
                        score++;
                    }
                }
                scoreForMissions.put(mission, score);
            }
        }
        model.addAttribute("scoreForMissions", scoreForMissions);

        Map<JeuEntity, Integer> scoreForJeux = new HashMap<>();
        for(JeuEntity jeu : jeuxForId) {
            score = 0;
            for(MissionEntity mission : missionsForId.get(jeu)) {
                if(scoreForMissions.get(mission) == objectifsForId.get(mission).size()) {
                    score++;
                }
            }
            scoreForJeux.put(jeu, score);
        }
        model.addAttribute("scoreForJeux", scoreForJeux);

        return "Apprenants/afficheApprenant";
    }

    @RequestMapping(value = "ajouter", method = RequestMethod.GET)
    public String ajouterApprenant(Model model) {
        ApprenantEntity apprenant = new ApprenantEntity();
        model.addAttribute("apprenant", apprenant);
        return "Forms/FormApprenant";
    }

    @RequestMapping(value = "creer", method = RequestMethod.POST)
    public String insererApprenant(@ModelAttribute("apprenant") @Validated ApprenantEntity apprenant, BindingResult result, RedirectAttributes redirectAttributes) {
        if(result.hasErrors()) {
            return "Forms/FormApprenant";
        }
        else {
            SA.saveOrUpdate(apprenant);
            redirectAttributes.addFlashAttribute("css", "success");
            redirectAttributes.addFlashAttribute("msg", "Apprenant enregistré avec succès.");
            return "redirect:/apprenants/" + apprenant.getNumapprenant();
        }
    }

    @RequestMapping(value = "{id}/modifier", method = RequestMethod.GET)
    public String modifierApprenant(@PathVariable("id") int id, Model model) {
        ApprenantEntity apprenant = SA.getById(id);
        model.addAttribute("apprenant", apprenant);

        return "Forms/FormApprenant";
    }

    @RequestMapping(value = "{id}/supprimer", method = RequestMethod.POST)
    public String supprimerApprenant(@PathVariable("id") int id, RedirectAttributes redirectAttributes) {
        SA.remove(id);

        redirectAttributes.addFlashAttribute("css", "success");
        redirectAttributes.addFlashAttribute("msg", "Apprenant supprimé avec succès.");

        return "redirect:/apprenants";
    }

    @RequestMapping(value = "{id}/inscrire", method = RequestMethod.GET)
    public String inscrireApprenant(@PathVariable("id") int id, Model model) {
        InscriptionEntity inscription = new InscriptionEntity();
        ApprenantEntity apprenant = SA.getById(id);
        inscription.setNumapprenant(apprenant.getNumapprenant());
        model.addAttribute("inscription", inscription);

        List<ApprenantEntity> apprenants = SA.getAll();
        model.addAttribute("apprenants", apprenants);

        ServiceJeu SJ = new ServiceJeu();
        List<JeuEntity> jeux = SJ.getAll();
        model.addAttribute("jeux", jeux);

        return "Forms/FormInscriptionJeu";
    }

    @RequestMapping(value = "inscription", method = RequestMethod.POST)
    public String inscriptionApprenant(@ModelAttribute("inscription") @Validated InscriptionEntity inscription, BindingResult result, RedirectAttributes redirectAttributes) {
        if(result.hasErrors()) {
            return "Forms/FormInscriptionJeu";
        }
        else {
            ServiceInscription SI = new ServiceInscription();
            SI.saveOrUpdate(inscription);
            redirectAttributes.addFlashAttribute("css", "success");
            redirectAttributes.addFlashAttribute("msg", "Apprenant inscrit avec succès.");
            return "redirect:/apprenants/" + inscription.getNumapprenant();
        }
    }

    @RequestMapping(value="{idA}/desinscrire/{idJ}", method = RequestMethod.POST)
    public String desinscrireApprenant(@PathVariable("idA") int idA, @PathVariable("idJ") int idJ, RedirectAttributes redirectAttributes) {
        ServiceInscription SI = new ServiceInscription();
        SI.remove(idA, idJ);

        redirectAttributes.addFlashAttribute("css", "success");
        redirectAttributes.addFlashAttribute("msg", "Apprenant désinscrit avec succès.");

        return "redirect:/apprenants/" + idA;
    }
}
