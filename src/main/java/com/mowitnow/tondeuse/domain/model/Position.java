package com.mowitnow.tondeuse.domain.model;

import lombok.*;
import org.apache.commons.lang3.StringUtils;

import java.util.Objects;

/**
 * @author berrami badr
 * @since 0.0.1-SNAPSHOT
 * Description : Cette classe permettant de créer des instances de type {@link Position}
 */
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Position
{
    /** position horizentale (N° ligne)**/
    private Integer positionX;

    /** position verticale (N° colonne)**/
    private Integer positionY;

    /**
     * Cette méthode permet de vérifier si la position en paramètre
     * dépasse les limites de la pelouse
     * @param position : une instance de type {@link Position} représente la prochaine position de la tondeuse
     * @return un boolean indiquant le dépassement ou pas des bords de la pelouse.
     */
    public boolean verifierDepassementDePosition(Position position)
    {
        return (position.getPositionX() > this.getPositionX()) ||  (position.getPositionY() > this.getPositionY())
                || (position.getPositionX() < 0) || (position.getPositionY() < 0);
    }

    @Override
    public String toString() {
        return positionX + StringUtils.SPACE + positionY;
    }

    @Override
    public boolean equals(Object objet)
    {
        if (Objects.isNull(objet))
        {
            return false;
        }
        var position = (Position) objet;

        if (!Objects.equals(this.getPositionX(), position.getPositionX()))
        {
            return false;
        }
        return Objects.equals(this.getPositionY(), position.getPositionY());
    }

}
