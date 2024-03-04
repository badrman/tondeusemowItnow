package com.mowitnow.tondeuse.domain.model;

import java.util.Arrays;
/**
 * @author berrami badr
 * @since 0.0.1-SNAPSHOT
 * Description : L'enumération des directions d'une tondeuse.
 *
 */
public enum Direction
{
    /** Direction vers le nord **/
    NORTH("N", "Direction vers le nord"),
    /** Direction vers l'ouest **/
    WEST("W", "Direction vers l'ouest"),
    /** Direction vers le sud **/
    SOUTH("S", "Direction vers le sud"),
    /** Direction vers l'est **/
    EAST("E","Direction vers l'est");

    /** le code direction : "N", "W", "S" et "E" **/
    private String code;

    /** description longue de la direction **/
    private String directionDescription;

    /** Direction à gauche de la direction actuelle **/
    private Direction directionPivoteeAGauche;

    /** Direction à droite de la direction actuelle **/
    private Direction directionPivoteeADroite;

    /*
     *  Bloc static pour initialiser les directions directes (à gauche et à droite)
     *  de chaque direction
     */
    static
    {
        // Déterminer les directions après avoir pivoté la direction de la tendeuse.
        // -- Direction NORTH
        NORTH.directionPivoteeAGauche = WEST;
        NORTH.directionPivoteeADroite = EAST;
        // -- Direction WEST
        WEST.directionPivoteeAGauche = SOUTH;
        WEST.directionPivoteeADroite = NORTH;
        // -- Direction SOUTH
        SOUTH.directionPivoteeAGauche = EAST;
        SOUTH.directionPivoteeADroite = WEST;
        // -- Direction EAST
        EAST.directionPivoteeAGauche = NORTH;
        EAST.directionPivoteeADroite = SOUTH;
    }

    /**
     * Constructeur de l'enumeration avec en paramètre la direction et sa description
     *
     * @param code : la direction
     * @param directionDescription : la description de la direction
     */
    Direction(String code, String directionDescription)
    {
        this.code = code;
        this.directionDescription = directionDescription;
    }

    /**
     * Getter de {@link #code}
     * @return {@link #code}
     */
    public String getCode()
    {
        return code;
    }

    /**
     * Getter de {@link #directionDescription}
     * @return {@link #directionDescription}
     */
    public String getDirectionDescription()
    {
        return directionDescription;
    }

    /**
     * Getter de {@link #directionPivoteeAGauche}
     * @return {@link #directionPivoteeAGauche}
     */
    public Direction getDirectionPivoteeAGauche()
    {
        return directionPivoteeAGauche;
    }

    /**
     * Getter de {@link #directionPivoteeADroite}
     * @return {@link #directionPivoteeADroite}
     */
    public Direction getDirectionPivoteeADroite()
    {
        return directionPivoteeADroite;
    }

    /**
     * Une méthode utilitaire permettant de récupérer une {@link Direction}
     * à partir d'une chaine de caractère en entrée.
     *
     * @param direction : la direction à récupérer
     * @return une instance de type {@link Direction}
     */
    public static Direction getDirectionEnumParCaractere(String direction)
    {
        return Arrays.asList(Direction.values()).stream()
                .filter(direction1 -> direction1.getCode().equals(direction))
                .findFirst()
                .orElse(null);
    }
}