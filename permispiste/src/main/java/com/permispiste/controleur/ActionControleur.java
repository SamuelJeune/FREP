package com.permispiste.controleur;

import com.permispiste.metier.ActionEntity;
import com.permispiste.metier.EstAssocieEntity;
import com.permispiste.metier.MissionEntity;
import com.permispiste.metier.ObjectifEntity;
import com.permispiste.service.ServiceAction;
import com.permispiste.service.ServiceEstAssocie;
import com.permispiste.service.ServiceMission;
import com.permispiste.service.ServiceObjectif;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.swing.*;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("actions")
public class ActionControleur {
    private static ServiceAction SA = new ServiceAction();

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String afficheActions(Model model) {
        List<ActionEntity> actions = SA.getAll();
        model.addAttribute("actions", actions);

        return "Actions/actions";
    }

    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public String afficheAction(@PathVariable("id") int id, Model model) {
        ActionEntity action = SA.getById(id);
        model.addAttribute("action", action);

        ServiceEstAssocie SEA = new ServiceEstAssocie();
        List<EstAssocieEntity> associations = SEA.getByAction(id);
        ServiceObjectif SO = new ServiceObjectif();
        List<ObjectifEntity> objectifsForAction = associations.stream().map(a -> SO.getById(a.getNumobjectif())).collect(Collectors.toList());
        model.addAttribute("objectifs", objectifsForAction);

        return "Actions/afficheAction";
    }

    @RequestMapping(value = "ajouter", method = RequestMethod.GET)
    public String ajouterAction(Model model) {
        ActionEntity action = new ActionEntity();
        model.addAttribute("action", action);

        List<ActionEntity> actions = SA.getAll();
        model.addAttribute("actions", actions);

        return "Forms/FormAction";
    }

    @RequestMapping(value = "creer", method = RequestMethod.POST)
    public String insererAction(@ModelAttribute("action") @Validated ActionEntity action, BindingResult result, RedirectAttributes redirectAttributes) {
        if(result.hasErrors()) {
            return "Forms/FormAction";
        }
        else {
            SA.saveOrUpdate(action);
            redirectAttributes.addFlashAttribute("css", "success");
            redirectAttributes.addFlashAttribute("msg", "Action crée avec succès.");
            return "redirect:/actions/" + action.getNumaction();
        }
    }

    @RequestMapping(value = "{id}/modifier", method = RequestMethod.GET)
    public String modifierAction(@PathVariable("id") int id, Model model) {
        ActionEntity action = SA.getById(id);
        model.addAttribute("action", action);

        List<ActionEntity> actions = SA.getAll();
        actions.remove(action);
        model.addAttribute("actions", actions);

        return "Forms/FormAction";
    }

    @RequestMapping(value = "{id}/supprimer", method = RequestMethod.POST)
    public String supprimerAction(@PathVariable("id") int id, RedirectAttributes redirectAttributes) {
        SA.remove(id);

        redirectAttributes.addFlashAttribute("css", "success");
        redirectAttributes.addFlashAttribute("msg", "Action supprimée avec succès.");

        return "redirect:/actions";
    }

    @RequestMapping(value = "{id}/ajouter-objectif", method = RequestMethod.GET)
    public String ajouterObjectif(@PathVariable("id") int id, Model model) {
        List<ActionEntity> actions = SA.getAll();
        model.addAttribute("actions", actions);

        ServiceObjectif SO = new ServiceObjectif();
        List<ObjectifEntity> objectifs = SO.getAll();
        model.addAttribute("objectifs", objectifs);

        EstAssocieEntity association = new EstAssocieEntity();
        ActionEntity action = SA.getById(id);
        association.setNumaction(action.getNumaction());
        model.addAttribute("association", association);

        return "Forms/FormAssociation";
    }

    @RequestMapping(value = "associer", method = RequestMethod.POST)
    public String associerObjectif(@ModelAttribute @Validated EstAssocieEntity association, BindingResult result, RedirectAttributes redirectAttributes) {
        if(result.hasErrors()) {
            return "Forms/FormAssociation";
        }
        else {
            ServiceEstAssocie SEA = new ServiceEstAssocie();
            SEA.saveOrUpdate(association);

            redirectAttributes.addFlashAttribute("css", "success");
            redirectAttributes.addFlashAttribute("msg", "Objectif ajouté avec succès.");
        }
        return "redirect:/actions/" + association.getNumaction();
    }

    @RequestMapping(value = "{idA}/retirer-objectif/{idO}", method = RequestMethod.POST)
    public String retirerObjectif(@PathVariable("idA") int idA, @PathVariable("idO") int idO, RedirectAttributes redirectAttributes) {
        ServiceEstAssocie SEA = new ServiceEstAssocie();
        SEA.remove(idA, idO);

        redirectAttributes.addFlashAttribute("css", "success");
        redirectAttributes.addFlashAttribute("msg", "Objectif retiré avec succès.");

        return "redirect:/actions/" + idA;
    }
}
