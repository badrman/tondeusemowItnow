package com.mowitnow.tondeuse.domain.exception;

/**
 * @author berrami badr
 * @since 0.0.1-SNAPSHOT
 * Description : Une classe exception custom, utilisée principalement pour gérer les exceptions
 *               de parsing
 *
 */
public class ParseLigneException extends RuntimeException
{
    /**
     * Constructeur de la classe avec un seul paramètre {@link #message}.
     * @param message : le message d'exceptions
     */
    public ParseLigneException(String message)
    {
        super(message);
    }
}
