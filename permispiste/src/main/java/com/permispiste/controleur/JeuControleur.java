package com.permispiste.controleur;

import com.permispiste.metier.*;
import com.permispiste.service.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
@RequestMapping("jeux")
public class JeuControleur {
    private static ServiceJeu SJ = new ServiceJeu();

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String afficheJeux(Model model) {
        List<JeuEntity> jeux = SJ.getAll();
        model.addAttribute("jeux", jeux);
        return "Jeux/jeux";
    }

    @RequestMapping(value = "ajouter", method = RequestMethod.GET)
    public String ajouterJeu(Model model) {
        JeuEntity jeu = new JeuEntity();
        model.addAttribute("jeu", jeu);

        return "Forms/FormJeu";
    }

    @RequestMapping(value = "creer", method = RequestMethod.POST)
    public String insererJeu(@ModelAttribute("jeu") @Validated JeuEntity jeu, BindingResult result, RedirectAttributes redirectAttributes) {
        if(result.hasErrors()) {
            return "Forms/FormJeu";
        }
        else {
            SJ.saveOrUpdate(jeu);
            redirectAttributes.addFlashAttribute("css", "success");
            redirectAttributes.addFlashAttribute("msg", "Jeu enregistré avec succès.");
            return "redirect:/jeux/" + jeu.getNumjeu();
        }
    }

    @RequestMapping(value = "{id}/modifier", method = RequestMethod.GET)
    public String modifierJeu(@PathVariable("id") int id, Model model) {
        JeuEntity jeu = SJ.getById(id);
        model.addAttribute("jeu", jeu);

        return "Forms/FormJeu";
    }

    @RequestMapping(value = "{id}/supprimer", method = RequestMethod.POST)
    public String supprimerJeu(@PathVariable("id") int id, RedirectAttributes redirectAttributes) {
        SJ.remove(id);

        redirectAttributes.addFlashAttribute("css", "success");
        redirectAttributes.addFlashAttribute("msg", "Jeu supprimé avec succès.");

        return "redirect:/jeux";
    }

    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public String afficheJeu(@PathVariable("id") int id, Model model) {
        JeuEntity jeu = SJ.getById(id);
        model.addAttribute("jeu", jeu);

        ServiceInscription SIns = new ServiceInscription();
        List<InscriptionEntity> inscriptionForJeu = SIns.getByJeu(id);
        ServiceApprenant SAp = new ServiceApprenant();
        List<ApprenantEntity> apprenantsForJeu = inscriptionForJeu.stream().map(i -> SAp.getById(i.getNumapprenant())).collect(Collectors.toList());
        model.addAttribute("apprenants", apprenantsForJeu);

        ServiceMission SM = new ServiceMission();
        ServiceFixe SF = new ServiceFixe();
        ServiceObjectif SO = new ServiceObjectif();
        ServiceEstAssocie SEA = new ServiceEstAssocie();
        ServiceAction SAc = new ServiceAction();
        ServiceIndicateur SInd = new ServiceIndicateur();

        List<MissionEntity> missionsForJeu = SM.getByJeu(id);
        model.addAttribute("missions", missionsForJeu);

        List<ObjectifEntity> objectifsForJeu = new ArrayList<>();
        for(MissionEntity mission : missionsForJeu) {
            objectifsForJeu.addAll(SF.getByMission(mission.getNummission()).stream().map(f -> SO.getById(f.getNumobjectif())).collect(Collectors.toList()));
        }

        List<ActionEntity> actionsForJeu = new ArrayList<>();
        for(ObjectifEntity objectif : objectifsForJeu) {
            actionsForJeu.addAll(SEA.getByObjectif(objectif.getNumobjectif()).stream().map(ea -> SAc.getById(ea.getNumaction())).collect(Collectors.toList()));
        }
        actionsForJeu = actionsForJeu.stream().distinct().sorted().collect(Collectors.toList());
        model.addAttribute("actions", actionsForJeu);

        Map<ActionEntity, IndicateurEntity> indicateursForJeu = new HashMap<>();
        actionsForJeu.forEach(a -> indicateursForJeu.put(a, SInd.getByAction(a.getNumaction())));
        model.addAttribute("indicateurs", indicateursForJeu);

        return "Jeux/afficheJeu";
    }
}
