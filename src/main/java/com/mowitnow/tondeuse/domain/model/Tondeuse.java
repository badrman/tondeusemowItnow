package com.mowitnow.tondeuse.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;

import java.util.Objects;

/**
 * @author berrami badr
 * @since 0.0.1-SNAPSHOT
 * Description : Cette classe permettant de créer des instances de type {@link Tondeuse}
 */
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Tondeuse
{
    /** Position actuelle de la tondeuse**/
    private Position position;
    /** Direction de la tondeuse **/
    private Direction direction;
    /** Un objet regroupant la liste des commandes à exécuter **/
    private TondeuseCommandes commandes;


    @Override
    public boolean equals(Object objet) {
        if (Objects.isNull(objet))
        {
            return false;
        }
        var tondeuse = (Tondeuse) objet;
        if (Objects.nonNull(this.position) && !this.position.equals(tondeuse.getPosition()))
        {
            return false;
        }
        if (Objects.nonNull(this.direction) && !this.direction.equals(tondeuse.getDirection()))
        {
            return false;
        }
        return Objects.equals(this.commandes, tondeuse.getCommandes());
    }

    @Override
    public String toString()
    {
        return position.toString() + StringUtils.SPACE + direction.getCode();
    }
}
