package com.permispiste.controleur;

import com.permispiste.service.ServiceApprenant;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
public class mainControleur {
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView afficheIndex(HttpServletRequest request, HttpServletResponse response) {
        return new ModelAndView("index");
    }

    @RequestMapping(value = "/apprenants", method = RequestMethod.GET)
    public ModelAndView afficheApprenants(HttpServletRequest request, HttpServletResponse response) {
        ServiceApprenant SA = new ServiceApprenant();
        List<ApprenantEntity> apprenants = SA.getAll();
        request.setAttribute("apprenants", apprenants);
        return new ModelAndView("apprenants");
    }
}
