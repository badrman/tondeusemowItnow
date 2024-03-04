package com.mowitnow.tondeuse.domain.services.implementation;

import com.mowitnow.tondeuse.domain.constantes.ErreursMessages;
import com.mowitnow.tondeuse.domain.constantes.PatternConstantes;
import com.mowitnow.tondeuse.domain.exception.ParseLigneException;
import com.mowitnow.tondeuse.domain.model.Pelouse;
import com.mowitnow.tondeuse.domain.model.Position;
import com.mowitnow.tondeuse.domain.services.ParseurLigne;
import org.apache.commons.lang3.StringUtils;

import java.util.regex.Pattern;


/**
 * @author berrami badr
 * @since 0.0.1-SNAPSHOT
 * Description : Une implémentation de l'interface {@link ParseurLigne} permettant
 *               de parser une ligne des coordonnées de pelouse.
 */
public class ParseurLignePelouseImpl implements ParseurLigne
{

    /**
     * @see ParseurLigne#lireLigneDeFichier(String)
     */
    @Override
    public Pelouse lireLigneDeFichier(String ligne)
    {
        if (StringUtils.isBlank(ligne))
        {
            throw new ParseLigneException(ErreursMessages.LIGNE_PELOUSE_COORDONNEES_VIDE_EXCEPTION_MESSAGE);
        }
        if (!Pattern.matches(PatternConstantes.PELOUSE_COORDONNES_PATTERN, ligne))
        {
            throw new ParseLigneException(ErreursMessages.LIGNE_PELOUSE_COORDONNEES_EXCEPTION_MESSAGE);
        }
        var pelouseCoordonnees = ligne.split(StringUtils.SPACE);
        return new Pelouse(new Position(Integer.parseInt(pelouseCoordonnees[0]), Integer.parseInt(pelouseCoordonnees[1])), null);
    }

    /**
     * @see ParseurLigne#getClassTraite()
     */
    @Override
    public Class getClassTraite() {
        return Pelouse.class;
    }
}