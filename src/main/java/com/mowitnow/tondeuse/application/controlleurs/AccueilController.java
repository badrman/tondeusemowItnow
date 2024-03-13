package com.mowitnow.tondeuse.application.controlleurs;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author berrami badr
 * @since 0.0.1-SNAPSHOT
 *
 * Description : Ce controlleur permet de servir la page d'accueil de l'application : index.html
 */
@Controller
public class AccueilController
{
    @GetMapping("/index")
    public String indexUrl()
    {
        return "redirect:/";
    }
}
