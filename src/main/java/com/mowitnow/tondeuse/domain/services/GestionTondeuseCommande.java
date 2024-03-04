package com.mowitnow.tondeuse.domain.services;

import com.mowitnow.tondeuse.domain.model.Commande;
import com.mowitnow.tondeuse.domain.model.Position;
import com.mowitnow.tondeuse.domain.model.Tondeuse;

/**
 * @author berrami badr
 * @since 0.0.1-SNAPSHOT
 * Description : Interface de gestion des objets {@link com.mowitnow.tondeuse.domain.model.TondeuseCommandes}
 * Implémentation : {@link com.mowitnow.tondeuse.domain.services.implementation.GestionTondeuseCommandeImpl}
 */
public interface GestionTondeuseCommande
{
    /**
     * Cette méthode permet d'exécuter une commande sur l'objet {@link Tondeuse}.
     * @param commande : une instance de type {@link Commande} représente la commande à exécuter
     * @param tondeuse : une instance de type {@link Tondeuse} représente la tondeuse en cours de traitement
     * @param positionMaximale : une instance de type {@link Position} représente le coin superieur à droite de pelouse
     */
    void executerLaCommande(Commande commande, Tondeuse tondeuse, Position positionMaximale);
}
