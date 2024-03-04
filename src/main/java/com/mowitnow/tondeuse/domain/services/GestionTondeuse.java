package com.mowitnow.tondeuse.domain.services;

import com.mowitnow.tondeuse.domain.model.Position;
import com.mowitnow.tondeuse.domain.model.Tondeuse;

/**
 * @author berrami badr
 * @since 0.0.1-SNAPSHOT
 * Description : Interface de gestion des objets {@link Tondeuse}
 * Implémentation : {@link com.mowitnow.tondeuse.domain.services.implementation.GestionTondeuseImpl}
 */
public interface GestionTondeuse
{
    /**
     * Cette méthode permet de déclencher une tondeuse ({@link Tondeuse})
     * @param tondeuse : une instance de type {@link Tondeuse}
     * @param positionMaximale : une instance de type {@link Position} représente le coin superieur à droite de pelouse
     */
    void demarrerTondeuse(Tondeuse tondeuse, Position positionMaximale);
}
