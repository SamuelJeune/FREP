package com.permispiste.controleur;

import com.permispiste.metier.ApprenantEntity;
import com.permispiste.service.ServiceApprenant;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class mainControleur {
    @RequestMapping({"", "/"})
    public String afficheIndex() {
        return "index";
    }

    @RequestMapping(value = "search", method = RequestMethod.GET)
    public String recherche(@RequestParam("q")  String recherche, Model model) {
        ServiceApprenant SA = new ServiceApprenant();
        List<ApprenantEntity> result = SA.getByName(recherche);
        if(result.size() == 1) {
            return "redirect:/apprenants/" + result.get(0).getNumapprenant();
        }
        else {
            model.addAttribute("recherche", recherche);
            model.addAttribute("apprenants", result);
            return "recherche";
        }
    }
}
