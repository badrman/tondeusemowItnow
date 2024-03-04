package com.mowitnow.tondeuse.application.exception;

/**
 * @author berrami badr
 * @since 0.0.1-SNAPSHOT
 * Description : Une classe exception custom utilisée principalement pour gérer le cas d'un fichier de coordonnées
 *               vide
 *
 */
public class FichierVideException extends RuntimeException
{
    /**
     * Constructeur de la classe avec un seul paramètre {@link #message}.
     * @param message : le message d'exceptions
     */
    public FichierVideException(String message)
    {
        super(message);
    }
}
