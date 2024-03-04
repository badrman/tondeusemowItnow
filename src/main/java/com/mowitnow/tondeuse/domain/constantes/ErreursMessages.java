package com.mowitnow.tondeuse.domain.constantes;

/**
 * @author berrami badr
 * @since 0.0.1-SNAPSHOT
 * Description : Une classe constante regroupant les messages d'erreurs à afficher
 *               lors de la géneration des exceptions.
 */
public final class ErreursMessages
{
    /**
     *  Message d'erreur si les coordonnées de pelouse ne sont pas conformes
     */
    public static final String LIGNE_PELOUSE_COORDONNEES_EXCEPTION_MESSAGE = "Coordonnées de pelouse incorrectes";
    /**
     *  Message d'erreur si la ligne de pelouse est vide
     */
    public static final String LIGNE_PELOUSE_COORDONNEES_VIDE_EXCEPTION_MESSAGE = "Ligne des coordonnées de pelouse vide";
    /**
     *  Message d'erreur si les coordonnées de tondeuse ne sont pas conformes
     */
    public static final String LIGNE_TONDEUSE_COORDONNEES_EXCEPTION_MESSAGE = "Coordonnées de tondeuse incorrectes";
    /**
     *  Message d'erreur si la ligne des coordonnées de tondeuse est vide
     */
    public static final String LIGNE_TONDEUSE_COORDONNEES_VIDE_EXCEPTION_MESSAGE = "Ligne des coordonnées de tondeuse vide";

    /**
     *  Message d'erreur si les commandes de tondeuse ne sont pas conformes
     */
    public static final String LIGNE_TONDEUSE_COMMANDES_EXCEPTION_MESSAGE = "Commandes de tondeuse incorrectes";

    /**
     *  Message d'erreur si la ligne des commandes de tondeuse est vide
     */
    public static final String LIGNE_TONDEUSE_COMMANDES_VIDE_EXCEPTION_MESSAGE = "Ligne des commandes vide";
    /**
     *  Message d'erreur si la direction fournie en paramètre n'est pas connue.
     */
    public static final String DIRECTION_INCORRECTE = "Direction incorrecte";
    /**
     *  Message d'erreur si la commande fournie en paramètre n'est pas connue.
     */
    public static final String COMMANDE_INCORRECTE = "Commande Incorrecte";
}
