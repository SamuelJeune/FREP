package com.permispiste.controleur;

import com.permispiste.metier.FixeEntity;
import com.permispiste.metier.MissionEntity;
import com.permispiste.metier.ObjectifEntity;
import com.permispiste.service.ServiceFixe;
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
@RequestMapping("objectifs")
public class ObjectifControleur {
    private static ServiceObjectif SO = new ServiceObjectif();

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String afficheObjectifs(Model model) {
        List<ObjectifEntity> objectifs = SO.getAll();
        model.addAttribute("objectifs", objectifs);

        return "Objectifs/objectifs";
    }


    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public String afficheObjectif(@PathVariable("id") int id, Model model) {
        ObjectifEntity objectif = SO.getById(id);
        model.addAttribute("objectif", objectif);

        ServiceFixe SF = new ServiceFixe();
        List<FixeEntity> fixesForObjectif = SF.getByObjectif(id);
        ServiceMission SM = new ServiceMission();
        List<MissionEntity> missionsForObjecif = fixesForObjectif.stream().map(f -> SM.getById(f.getNummission())).collect(Collectors.toList());
        model.addAttribute("missions", missionsForObjecif);

        return "Objectifs/afficheObjectif";
    }

    @RequestMapping(value = "ajouter", method = RequestMethod.GET)
    public String ajouterObjectif(Model model) {
        ObjectifEntity objectif = new ObjectifEntity();
        model.addAttribute("objectif", objectif);
        return "Forms/FormObjectif";
    }

    @RequestMapping(value = "creer", method = RequestMethod.POST)
    public String insererObjectif(@ModelAttribute("objectif") @Validated ObjectifEntity objectif, BindingResult result, RedirectAttributes redirectAttributes) {
        if(result.hasErrors()) {
            return "Forms/FormObjectif";
        }
        else {
            SO.saveOrUpdate(objectif);
            redirectAttributes.addFlashAttribute("css", "success");
            redirectAttributes.addFlashAttribute("msg", "Objectif enregistré avec succès.");
            return "redirect:/objectifs/" + objectif.getNumobjectif();
        }
    }

    @RequestMapping(value = "{id}/modifier", method = RequestMethod.GET)
    public String modifierObjectif(@PathVariable("id") int id, Model model) {
        ObjectifEntity objectif = SO.getById(id);
        model.addAttribute("objectif", objectif);

        return "Forms/FormObjectif";
    }

    @RequestMapping(value = "{id}/supprimer", method = RequestMethod.POST)
    public String supprimerObjectif(@PathVariable("id") int id, RedirectAttributes redirectAttributes) {
        SO.remove(id);

        redirectAttributes.addFlashAttribute("css", "success");
        redirectAttributes.addFlashAttribute("msg", "Objectif supprimé avec succès.");

        return "redirect:/objectifs";
    }

    @RequestMapping(value = "{id}/ajouter-mission", method = RequestMethod.GET)
    public String ajouterMission(@PathVariable("id") int id, Model model) {
        ServiceMission SM = new ServiceMission();
        List<MissionEntity> missions = SM.getAll();
        model.addAttribute("missions", missions);

        List<ObjectifEntity> objectifs = SO.getAll();
        model.addAttribute("objectifs", objectifs);

        FixeEntity fixe = new FixeEntity();
        ObjectifEntity objectif = SO.getById(id);
        fixe.setNumobjectif(objectif.getNumobjectif());
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
            redirectAttributes.addFlashAttribute("msg", "Mission ajoutée avec succès.");

            return "redirect:/objectifs/" + fixe.getNumobjectif();
        }
    }

    @RequestMapping(value = "{idO}/retirer-mission/{idM}", method = RequestMethod.POST)
    public String retirerMission(@PathVariable("idO") int idO, @PathVariable("idM") int idM, RedirectAttributes redirectAttributes) {
        ServiceFixe SF = new ServiceFixe();
        SF.remove(idM, idO);

        redirectAttributes.addFlashAttribute("css", "success");
        redirectAttributes.addFlashAttribute("msg", "Mission retirée avec succès.");

        return "redirect:/objectifs/" + idO;
    }
}
