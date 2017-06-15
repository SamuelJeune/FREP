package com.permispiste.controleur;

import com.permispiste.metier.ObjectifEntity;
import com.permispiste.service.ServiceObjectif;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
@RequestMapping("objectifs")
public class ObjectifControleur {
    private static ServiceObjectif SO = new ServiceObjectif();

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String afficheJeux(Model model) {
        List<ObjectifEntity> objectifs = SO.getAll();
        model.addAttribute("objectifs", objectifs);

        return "Objectifs/objectifs";
    }
}
