package com.mowitnow.tondeuse.application.controlleurs;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


/**
 * @author berrami badr
 * @since 0.0.1-SNAPSHOT
 *
 * Description : Ce controlleur personalisé permet
 * sert à surcharger le comportement de base de spring boot pour le traitement des
 * URLs inconnus "/error"
 * --> L'url "/error" est interprété par la page pageintrouvable.html
 */
@Controller
public class CustomErrorController implements ErrorController {
    @GetMapping("/error")
    public String handleError()
    {
        return "pageintrouvable";
    }
}
