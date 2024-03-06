package com.mowitnow.tondeuse.application.controlleurs;

import com.mowitnow.tondeuse.application.exception.FichierVideException;
import com.mowitnow.tondeuse.domain.exception.ParseLigneException;
import com.mowitnow.tondeuse.domain.services.GestionPelouse;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;


/**
 * @author berrami badr
 * @since 0.0.1-SNAPSHOT
 * Description : Ce controlleur permet de traiter le fichier d'entrée des tondeuses en provenance
 * du Front. Il permet entre autre, d'appeller le service {@link GestionPelouse}
 * pour initialiser la pelouse et puis, déclencher les tondeuses.
 */
@Setter
@Getter
@Controller
public class TondeusesController
{

    private GestionPelouse gestionPelouse;

    public TondeusesController(GestionPelouse gestionPelouse)
    {
        this.gestionPelouse = gestionPelouse;
    }

    /**
     *
     * @param fichier : une instance de type {@link MultipartFile} représente le fichier d'entree
     * @param model : une instance de type {@link Model}
     * @return le nom de page à afficher :
     *    - "detaildetondeuses" pour affichage des coordonnées des tondeuses après exécution.
     *    - "fichierentreevide" si le fichier en entrée est vide.
     * @throws IOException : une instance de type {@link IOException}
     */
    @PostMapping(path = "demarrerTondeuses", consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
    public String demarrerTondeuses(@RequestPart MultipartFile fichier, Model model) throws IOException {
        if (fichier.isEmpty())
        {
            throw new FichierVideException("Fichier des coordonnees en entré est vide");
        }
        var pelouse = gestionPelouse.intialiserEtLancerTendeuse(new BufferedReader(
                new InputStreamReader(fichier.getInputStream(), StandardCharsets.UTF_8)));
        model.addAttribute("coordonnees", pelouse.toString());
        return "detaildetondeuses";
    }

    /**
     * Cette méthode sert à gérer les exceptions de Parsing
     * @param exception : une instance de type {@link ParseLigneException}
     * @return une instance de type {@link ModelAndView}
     */
    @ExceptionHandler(ParseLigneException.class)
    public ModelAndView gererParsingException(ParseLigneException exception)
    {
        var modelAndView = new ModelAndView();
        modelAndView.addObject("erreurMessage", exception.getMessage());
        modelAndView.setViewName("erreur");
        return modelAndView;
    }

    /**
     * Cette méthode sert à gérer l'exception de fichier des coordonnées vide
     * @return une instance de type {@link ModelAndView}
     */
    @ExceptionHandler(FichierVideException.class)
    public ModelAndView gererFichierVideException()
    {
        var modelAndView = new ModelAndView();
        modelAndView.setViewName("fichierentreevide");
        return modelAndView;
    }
}
