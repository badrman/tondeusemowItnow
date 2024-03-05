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
 * Description : Cette classe permettant de créer des instances de type {@link Pelouse}
 */
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Pelouse
{
    /** La position de point supérieur à droite **/
    private Position dimensionPelouse;

    /** La liste des tondeuse rattâchés au pelouse **/
    private List<Tondeuse> tondeuses;

    @Override
    public boolean equals(Object objet)
    {
        if (Objects.isNull(objet))
        {
            return false;
        }
        var pelouse = (Pelouse) objet;

        if (Objects.nonNull(pelouse.getDimensionPelouse()) && !pelouse.getDimensionPelouse().equals(this.getDimensionPelouse()))
        {
            return false;
        }
        return Objects.equals(pelouse.getTondeuses(), this.getTondeuses());
    }

    @Override
    public String toString() {
        var messageAAffichier = new StringBuilder();
        if (Objects.nonNull(tondeuses))
        {
            tondeuses.stream().forEach(tondeuse -> messageAAffichier
                    .append(tondeuse.toString())
                    .append("\n"));
        }
        return messageAAffichier.toString();
    }

    /**
     * Cette methode permet d'ajouter une nouvelle tondeuse au pelouse.
     * @param tondeuse : une instance de type {@link Tondeuse}
     */
    public void ajouterTondeuse(Tondeuse tondeuse)
    {
        if (CollectionUtils.isEmpty(tondeuses))
        {
            tondeuses = new ArrayList<>();
        }
        tondeuses.add(tondeuse);
    }
}
