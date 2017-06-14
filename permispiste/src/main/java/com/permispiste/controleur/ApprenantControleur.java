package com.permispiste.controleur;

import com.permispiste.metier.ApprenantEntity;
import com.permispiste.service.ServiceApprenant;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

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
        return "afficheApprenant";
    }

    @RequestMapping(value = "ajouter", method = RequestMethod.GET)
    public String ajouterApprenant(Model model) {
        ApprenantEntity apprenant = new ApprenantEntity();
        model.addAttribute("apprenant", apprenant);
        return "ajouterApprenant";
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public String insererApprenant(@ModelAttribute("apprenant") @Validated ApprenantEntity apprenant, BindingResult result, RedirectAttributes redirectAttributes) {
        if(result.hasErrors()) {
            return "ajouterApprenant";
        }
        else {
            redirectAttributes.addFlashAttribute("css", "success");
            if(apprenant.getNumapprenant() == 0) {
                redirectAttributes.addFlashAttribute("msg", "Apprenant ajouté avec succès.");
            }
            else {
                redirectAttributes.addFlashAttribute("msg", "Apprenant modifié avec succès.");
            }
            SA.saveOrUpdate(apprenant);

            return "redirect:/apprenants" + apprenant.getNumapprenant();
        }
    }

    @RequestMapping(value = "{id}/supprimer", method = RequestMethod.POST)
    public String supprimerApprenant(@PathVariable("id") int id, RedirectAttributes redirectAttributes) {
        SA.remove(id);

        redirectAttributes.addFlashAttribute("css", "success");
        redirectAttributes.addFlashAttribute("msg", "Apprenant supprimé avec succès.");

        return "redirect:/apprenants";
    }


}
