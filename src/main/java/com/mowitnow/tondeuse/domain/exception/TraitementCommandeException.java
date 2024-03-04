package com.mowitnow.tondeuse.domain.exception;

/**
 * @author berrami badr
 * @since 0.0.1-SNAPSHOT
 * Description : Une classe exception custom, utilisée pour gérer les erreurs identifiées lors de
 *               traitement des commandes et/ou direction de la tondeuse.
 */
public class TraitementCommandeException extends RuntimeException
{
    /**
     * Constructeur de la classe avec un seul paramètre {@link #message}.
     * @param message : le message d'exceptions
     */
    public TraitementCommandeException(String message)
    {
        super(message);
    }
}
