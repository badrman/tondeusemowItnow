package com.mowitnow.tondeuse.domain.services.implementation;

import com.mowitnow.tondeuse.domain.model.Position;
import com.mowitnow.tondeuse.domain.model.Tondeuse;
import com.mowitnow.tondeuse.domain.services.GestionTondeuse;
import com.mowitnow.tondeuse.domain.services.GestionTondeuseCommande;
import lombok.Getter;
import lombok.Setter;

/**
 * @author berrami badr
 * @since 0.0.1-SNAPSHOT
 * Description : Une implémentation de l'interface {@link GestionTondeuse} permettant
 *               de déclencher l'exécution d'une tondeuse.
 */
@Setter
@Getter
public class GestionTondeuseImpl implements GestionTondeuse
{
    /** un attribut de type {@link GestionTondeuseCommande} pour exécuter les commandes **/
    private GestionTondeuseCommande gestionTondeuseCommande;
    /**
     * Constructeur pour créer une instance de service {@link GestionTondeuse}
     * @param gestionTondeuseCommande : une implémentation de service {@link GestionTondeuseCommande}
     */
    public GestionTondeuseImpl(GestionTondeuseCommande gestionTondeuseCommande)
    {
        this.gestionTondeuseCommande = gestionTondeuseCommande;
    }

    /**
     * @see GestionTondeuse#demarrerTondeuse(Tondeuse, Position)
     */
    @Override
    public void demarrerTondeuse(Tondeuse tondeuse, Position positionMaximale)
    {
        tondeuse.getCommandes().getCommandes().stream()
                .forEach(commande -> gestionTondeuseCommande.executerLaCommande(commande, tondeuse, positionMaximale));
    }
}