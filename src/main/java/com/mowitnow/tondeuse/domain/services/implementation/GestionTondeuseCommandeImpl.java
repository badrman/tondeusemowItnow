package com.mowitnow.tondeuse.domain.services.implementation;

import com.mowitnow.tondeuse.domain.constantes.ErreursMessages;
import com.mowitnow.tondeuse.domain.exception.TraitementCommandeException;
import com.mowitnow.tondeuse.domain.model.Commande;
import com.mowitnow.tondeuse.domain.model.Position;
import com.mowitnow.tondeuse.domain.model.Tondeuse;
import com.mowitnow.tondeuse.domain.services.GestionTondeuseCommande;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;
/**
 * @author berrami badr
 * @since 0.0.1-SNAPSHOT
 * Description : Une implémentation de l'interface {@link GestionTondeuseCommande} permettant
 *               de gérer les actions sur les tondeuses.
 */
@Setter
@Getter
public class GestionTondeuseCommandeImpl implements GestionTondeuseCommande
{
    /**
     * @see GestionTondeuseCommande#executerLaCommande(Commande, Tondeuse, Position)
     */
    @Override
    public void executerLaCommande(Commande commande, Tondeuse tondeuse, Position positionMaximal)
    {
        switch (commande)
        {
            case GAUCHE -> pivoterTondeuseAGauche(tondeuse);
            case DROITE -> pivoterTondeuseADroite(tondeuse);
            case AVANCER -> avancerTondeuse(tondeuse, positionMaximal);
            default ->
                // Ce cas n'est pas probable à se produire sauf si on fait des tests unitaires automatisés spécifiques
                new TraitementCommandeException(ErreursMessages.COMMANDE_INCORRECTE);
        }
    }

    /**
     * Cette méthode pour pivoter la tondeuse à droite.
     * @param tondeuse : une instance de type {@link Tondeuse}
     */
    private void pivoterTondeuseADroite(Tondeuse tondeuse)
    {
        tondeuse.setDirection(tondeuse.getDirection().getDirectionPivoteeADroite());
    }
    /**
     * Cette méthode pour pivoter la tondeuse à gauche.
     * @param tondeuse : une instance de type {@link Tondeuse}
     */
    private void pivoterTondeuseAGauche(Tondeuse tondeuse)
    {
        tondeuse.setDirection(tondeuse.getDirection().getDirectionPivoteeAGauche());
    }
    /**
     * Cette méthode permettant d'avancer la tondeuse dans la direction configurée.
     * @param tondeuse : une instance de type {@link Tondeuse}
     * @param positionMaximal : représente la position du coin supérieur à droite de pelouse
     */
    private void avancerTondeuse(Tondeuse tondeuse, Position positionMaximal)
    {
        var nouvellePosition = modifierCoordonnees(tondeuse);
        // vérifier si on a dépassé le perimètre de la pelouse ou pas
        // Le cas écheant, il faut garder la position avant l'action.
        if (Objects.nonNull(positionMaximal)
                && !positionMaximal.verifierDepassementDePosition(nouvellePosition))
        {
            tondeuse.setPosition(nouvellePosition);
        }
    }

    /**
     * Cette méthode permet de modifier les coordonnées d'une tondeuse.
     * @param tondeuse : une instance de type {@link Tondeuse}
     * @return une instance de type {@link Position}
     */
    private Position modifierCoordonnees(Tondeuse tondeuse)
    {
        var positionAvantAction = new Position(tondeuse.getPosition().getPositionX(), tondeuse.getPosition().getPositionY());
        switch (tondeuse.getDirection())
        {
            case NORTH -> positionAvantAction.setPositionY(positionAvantAction.getPositionY()+1);
            case WEST -> positionAvantAction.setPositionX(positionAvantAction.getPositionX()-1);
            case SOUTH -> positionAvantAction.setPositionY(positionAvantAction.getPositionY()-1);
            case EAST ->  positionAvantAction.setPositionX(positionAvantAction.getPositionX()+1);
            default ->
                // Ce cas n'est pas probable à se produire sauf si on fait des tests unitaires automatisés spécifiques
                throw new TraitementCommandeException(ErreursMessages.DIRECTION_INCORRECTE);
        }
        return positionAvantAction;
    }
}
