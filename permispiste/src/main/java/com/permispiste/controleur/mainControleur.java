package com.permispiste.controleur;

import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.*;

import javax.servlet.http.*;

/**
 * Created by Robin on 02/06/2017.
 */
@Controller
public class mainControleur {
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView afficheIndex(HttpServletRequest request, HttpServletResponse response) {
        return new ModelAndView("index");
    }
}
