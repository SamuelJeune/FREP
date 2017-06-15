package com.permispiste.controleur;

import com.permispiste.metier.JeuEntity;
import com.permispiste.metier.MissionEntity;
import com.permispiste.service.ServiceJeu;
import com.permispiste.service.ServiceMission;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("jeux")
public class JeuControleur {
    private static ServiceJeu SJ = new ServiceJeu();

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String afficheJeux(Model model) {
        List<JeuEntity> jeux = SJ.getAll();
        model.addAttribute("jeux", jeux);
        return "jeux";
    }

    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public String afficheJeu(@PathVariable("id") int id, Model model) {
        JeuEntity jeu = SJ.getById(id);
        model.addAttribute("jeu", jeu);

        ServiceMission SM = new ServiceMission();
        List<MissionEntity> missionsForJeu = SM.getByJeu(id);
        model.addAttribute("missions", missionsForJeu);

        return "afficheJeu";
    }
}
