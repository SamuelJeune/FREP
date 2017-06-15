package com.permispiste.controleur;

import com.permispiste.metier.ApprenantEntity;
import com.permispiste.metier.InscriptionEntity;
import com.permispiste.metier.JeuEntity;
import com.permispiste.service.ServiceApprenant;
import com.permispiste.service.ServiceInscription;
import com.permispiste.service.ServiceJeu;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("apprenants")
public class ApprenantControleur {
    private static ServiceApprenant SA = new ServiceApprenant();

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String afficheApprenants(Model model) {
        List<ApprenantEntity> apprenants = SA.getAll();
        model.addAttribute("apprenants", apprenants);
        return "apprenants";
    }

    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public String afficheApprenant(@PathVariable("id") int id, Model model) {
        ApprenantEntity apprenant = SA.getById(id);
        model.addAttribute("apprenant", apprenant);

        ServiceInscription SI = new ServiceInscription();
        List<InscriptionEntity> inscriptionsForApprenant = SI.getByApprenant(id);
        ServiceJeu SJ = new ServiceJeu();
        List<JeuEntity> jeuxForId = inscriptionsForApprenant.stream().map(i -> SJ.getById(i.getNumjeu())).collect(Collectors.toList());
        model.addAttribute("jeux", jeuxForId);

        return "afficheApprenant";
    }

    @RequestMapping(value = "ajouter", method = RequestMethod.GET)
    public String ajouterApprenant(Model model) {
        ApprenantEntity apprenant = new ApprenantEntity();
        model.addAttribute("apprenant", apprenant);
        return "FormApprenant";
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public String insererApprenant(@ModelAttribute("apprenant") @Validated ApprenantEntity apprenant, BindingResult result, RedirectAttributes redirectAttributes) {
        if(result.hasErrors()) {
            return "FormApprenant";
        }
        else {
            SA.saveOrUpdate(apprenant);
            redirectAttributes.addFlashAttribute("css", "success");
            if(apprenant.getNumapprenant() == 0) {
                redirectAttributes.addFlashAttribute("msg", "Apprenant ajouté avec succès.");
            }
            else {
                redirectAttributes.addFlashAttribute("msg", "Apprenant modifié avec succès.");
            }
            return "redirect:/apprenants/" + apprenant.getNumapprenant();
        }
    }

    @RequestMapping(value = "{id}/modifier", method = RequestMethod.GET)
    public String modifierApprenant(@PathVariable("id") int id, Model model) {
        ApprenantEntity apprenant = SA.getById(id);
        model.addAttribute("apprenant", apprenant);

        return "FormApprenant";
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

        return "inscriptionJeu";
    }

    @RequestMapping(value = "inscription", method = RequestMethod.POST)
    public String inscriptionApprenant(@ModelAttribute("inscription") @Validated InscriptionEntity inscription, BindingResult result, RedirectAttributes redirectAttributes) {
        if(result.hasErrors()) {
            return "inscriptionJeu";
        }
        else {
            ServiceInscription SI = new ServiceInscription();
            SI.saveOrUpdate(inscription);
            redirectAttributes.addFlashAttribute("css", "success");
            redirectAttributes.addFlashAttribute("msg", "Apprenant inscrit avec succès.");
            return "redirect:/apprenants/" + inscription.getNumapprenant();
        }
    }
}
