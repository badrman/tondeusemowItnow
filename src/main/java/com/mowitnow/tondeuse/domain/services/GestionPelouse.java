package com.mowitnow.tondeuse.domain.services;

import com.mowitnow.tondeuse.domain.model.Pelouse;

import java.io.BufferedReader;


/**
 * @author berrami badr
 * @since 0.0.1-SNAPSHOT
 * Description : Interface de gestion des objets {@link Pelouse}
 * Implémentation : {@link com.mowitnow.tondeuse.domain.services.implementation.GestionPelouseImpl}
 */
public interface GestionPelouse
{
    /**
     * Cette méthode permet d'initialiser une pelouse (une instance de type {@link Pelouse}) et
     * d'exécuter les tondeuses (des instances de type {@link com.mowitnow.tondeuse.domain.model.Tondeuse}
     * @param fichierEntree : un objet de type {@link BufferedReader} représente le fichier des paramètres
     * @return une instance de type {@link Pelouse}
     */
    Pelouse intialiserEtLancerTendeuse(BufferedReader fichierEntree);

    /**
     * Cette méthode permet d'initialiser une pelouse (une instance de type {@link Pelouse})
     * @param fichierEntree : un objet de type {@link BufferedReader} représente le fichier des paramètres
     * @return une instance de type {@link Pelouse}
     */
    Pelouse initialiserPelouse(BufferedReader fichierEntree);

}
