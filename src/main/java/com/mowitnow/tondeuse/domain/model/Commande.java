package com.mowitnow.tondeuse.domain.model;

import lombok.AllArgsConstructor;

import java.util.Arrays;

/**
 * @author berrami badr
 * @since 0.0.1-SNAPSHOT
 * Description : L'enumération des différentes commandes exécutées.
 */
@AllArgsConstructor
public enum Commande
{
    /** Action "Pivoter A Gauche" **/
    GAUCHE("G", "Pivoter vers le gauche"),

    /** Action "Pivoter A Droite" **/
    DROITE("D", "Pivoter vers la droite"),

    /** Action "Avancer" **/
    AVANCER("A", "Avancer d'une case");

    /**
     * La code de la commande ("A" : Avancer, "G": Gauche, "D: Droite")
     */
    private String code;
    /**
     * La description de la commende
     */
    private String commandeDescription;

    /**
     * Getter de {@link #code}
     * @return {@link #code}
     */
    public String getCode()
    {
        return code;
    }

    /**
     * Getter de {@link #commandeDescription}
     * @return {@link #commandeDescription}
     */
    public String getCommandeDescription() {
        return commandeDescription;
    }

    /**
     * Une méthode utilitaire permettant de récupérer une {@link Commande}
     * à partir d'une chaine de caractère en entrée.
     *
     * @param commande : la commande à récupérer
     * @return une instance de type {@link Commande}
     */
    public static Commande getCommandeEnumByCommande(String commande)
    {
        return Arrays.asList(Commande.values()).stream()
                .filter(commande1 -> commande1.getCode().equals(commande))
                .findFirst().orElse(null);
    }
}
