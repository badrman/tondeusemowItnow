package com.mowitnow.tondeuse.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.collections4.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author berrami badr
 * @since 0.0.1-SNAPSHOT
 * Description : Cette classe permettant de créer des instances de type {@link TondeuseCommandes}
 */
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class TondeuseCommandes
{
    /** La liste des commandes à exécuter par la tondeuse **/
    private List<Commande> commandes;

    /**
     * Cette méthode permet d'ajouter une instance de type {@link Commande} représentant une commande.
     * @param commande : une instance de type {@link Commande}
     */
    public void addCommande(Commande commande)
    {
        if (CollectionUtils.isEmpty(commandes))
        {
            commandes = new ArrayList<>();
        }
        commandes.add(commande);
    }

    @Override
    public boolean equals(Object tondeuseCommandes)
    {
        if (Objects.isNull(tondeuseCommandes))
        {
            return false;
        }
        return Objects.equals(commandes, ((TondeuseCommandes)tondeuseCommandes).getCommandes());
    }
}
