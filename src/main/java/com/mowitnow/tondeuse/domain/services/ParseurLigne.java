package com.mowitnow.tondeuse.domain.services;

/**
 * @author berrami badr
 * @since 0.0.1-SNAPSHOT
 * Description : Interface pour le parsing des fichiers des commandes tondeuses.
 *               Cette interface dispose de 3 implémentations :
 *               - {@link com.mowitnow.tondeuse.domain.services.implementation.ParseurLignePelouseImpl}
 *               - {@link com.mowitnow.tondeuse.domain.services.implementation.ParseurLigneCoordonneesTondeuseImpl}
 *               - {@link com.mowitnow.tondeuse.domain.services.implementation.ParseurLigneCommandesTondeuseImpl}
 */
public interface ParseurLigne
{
    /**
     * Cette méthode permet de lire une ligne de fichier d'entree, et de construire l'objet correspondant
     * {@link com.mowitnow.tondeuse.domain.model.Pelouse}, {@link com.mowitnow.tondeuse.domain.model.Tondeuse}
     * ou {@link com.mowitnow.tondeuse.domain.model.TondeuseCommandes}
     * @param ligne : la ligne à parser
     * @return l'objet constuit
     */
    Object lireLigneDeFichier(String ligne);

    /**
     * Cette méthode renvoie la classe traitée par le service de Parsing
     * @return une instance parmi ces instances : {@link com.mowitnow.tondeuse.domain.model.Pelouse},
     * {@link com.mowitnow.tondeuse.domain.model.Tondeuse} et {@link com.mowitnow.tondeuse.domain.model.TondeuseCommandes}
     */
    Class<? extends Object> getClassTraite();
}