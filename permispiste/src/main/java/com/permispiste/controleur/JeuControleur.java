package com.permispiste.controleur;

import com.permispiste.metier.ApprenantEntity;
import com.permispiste.metier.InscriptionEntity;
import com.permispiste.metier.JeuEntity;
import com.permispiste.metier.MissionEntity;
import com.permispiste.service.ServiceApprenant;
import com.permispiste.service.ServiceInscription;
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

        ServiceMission SM = new ServiceMission();
        List<MissionEntity> missionsForJeu = SM.getByJeu(id);
        model.addAttribute("missions", missionsForJeu);

        ServiceInscription SI = new ServiceInscription();
        List<InscriptionEntity> inscriptionForJeu = SI.getByJeu(id);
        ServiceApprenant SA = new ServiceApprenant();
        List<ApprenantEntity> apprenantsForJeu = inscriptionForJeu.stream().map(i -> SA.getById(i.getNumapprenant())).collect(Collectors.toList());
        model.addAttribute("apprenants", apprenantsForJeu);

        return "Jeux/afficheJeu";
    }
}
