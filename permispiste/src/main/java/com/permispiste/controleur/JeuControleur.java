package com.permispiste.controleur;

import com.permispiste.metier.JeuEntity;
import com.permispiste.service.ServiceJeu;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
@RequestMapping("jeux")
public class JeuControleur {
    @RequestMapping({"", "/"})
    public ModelAndView afficheApprenants(HttpServletRequest request, HttpServletResponse response) {
        ServiceJeu SJ = new ServiceJeu();
        List<JeuEntity> jeux = SJ.getAll();
        request.setAttribute("jeux", jeux);
        return new ModelAndView("jeux");
    }
}
