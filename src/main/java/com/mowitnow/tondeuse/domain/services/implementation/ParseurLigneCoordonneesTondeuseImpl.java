package com.mowitnow.tondeuse.domain.services.implementation;

import com.mowitnow.tondeuse.domain.constantes.ErreursMessages;
import com.mowitnow.tondeuse.domain.constantes.PatternConstantes;
import com.mowitnow.tondeuse.domain.exception.ParseLigneException;
import com.mowitnow.tondeuse.domain.model.Direction;
import com.mowitnow.tondeuse.domain.model.Position;
import com.mowitnow.tondeuse.domain.model.Tondeuse;
import com.mowitnow.tondeuse.domain.services.ParseurLigne;
import org.apache.commons.lang3.StringUtils;

import java.util.regex.Pattern;
/**
 * @author berrami badr
 * @since 0.0.1-SNAPSHOT
 * Description : Une implémentation de l'interface {@link ParseurLigne} permettant
 *               de parser une ligne des coordonnées de tondeuse.
 */
public class ParseurLigneCoordonneesTondeuseImpl implements ParseurLigne
{
    /**
     * @see ParseurLigne#lireLigneDeFichier(String)
     */
    @Override
    public Tondeuse lireLigneDeFichier(String ligne)
    {
        if (StringUtils.isBlank(ligne))
        {
            throw new ParseLigneException(ErreursMessages.LIGNE_TONDEUSE_COORDONNEES_VIDE_EXCEPTION_MESSAGE);
        }
        if (!Pattern.matches(PatternConstantes.TONDEUSE_COORDONNES_PATTERN, ligne))
        {
            throw new ParseLigneException(ErreursMessages.LIGNE_TONDEUSE_COORDONNEES_EXCEPTION_MESSAGE);
        }
        // Création d'une instance de type {@link Tondeuse}
        var coordonnees = ligne.split(StringUtils.SPACE);
        var position = new Position(Integer.parseInt(coordonnees[0]), Integer.parseInt(coordonnees[1]));
        return new Tondeuse(position, Direction.getDirectionEnumParCaractere(coordonnees[2]), null);
    }

    /**
     * @see ParseurLigne#getClassTraite()
     */
    @Override
    public Class getClassTraite()
    {
        return Tondeuse.class;
    }
}
