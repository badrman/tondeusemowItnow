package com.mowitnow.tondeuse.domain.constantes;

/**
 * @author berrami badr
 * @since 0.0.1-SNAPSHOT
 * Description : Une classe constante regroupant les expression régulières
 *               utilisées lors de parsing/validation de fichier texte en entrée.
 *
 */
public final class PatternConstantes
{
    /**
     * Expression régulière utilisée pour valider la ligne des coordonnées de la tondeuse.
     */
    public static final String TONDEUSE_COORDONNES_PATTERN = "^(\\d+) (\\d+) (N|W|S|E)$";

    /**
     * Expression régulière utilisée pour valider la ligne des commandes de la tondeuse.
     */
    public static final String TONDEUSE_COMMANDES_PATTERN = "^(G|D|A)+$";
    /**
     * Expression régulière utilisée pour valider la ligne des coordonnées de pelouse.
     */
    public static final String PELOUSE_COORDONNES_PATTERN = "^(\\d+) (\\d+)$";
}
