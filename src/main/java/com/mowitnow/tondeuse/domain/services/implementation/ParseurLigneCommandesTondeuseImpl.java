package com.mowitnow.tondeuse.domain.services.implementation;

import com.mowitnow.tondeuse.domain.constantes.ErreursMessages;
import com.mowitnow.tondeuse.domain.constantes.PatternConstantes;
import com.mowitnow.tondeuse.domain.exception.ParseLigneException;
import com.mowitnow.tondeuse.domain.model.Commande;
import com.mowitnow.tondeuse.domain.model.TondeuseCommandes;
import com.mowitnow.tondeuse.domain.services.ParseurLigne;
import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.regex.Pattern;

/**
 * @author berrami badr
 * @since 0.0.1-SNAPSHOT
 * Description : Une implémentation de l'interface {@link ParseurLigne} permettant
 *               de parser une ligne des commandes de tondeuse.
 */
public class ParseurLigneCommandesTondeuseImpl implements ParseurLigne
{
    /**
     * @see ParseurLigne#lireLigneDeFichier(String)
     * Crée un objet de type {@link TondeuseCommandes}
     */
    @Override
    public TondeuseCommandes lireLigneDeFichier(String ligne)
    {
        // Vérifier si la ligne pouleuse est vide. Le cas échéant,
        // Déclencher une exception indiquant que la ligne est vide.
        if (StringUtils.isBlank(ligne))
        {
            throw new ParseLigneException(ErreursMessages.LIGNE_TONDEUSE_COMMANDES_VIDE_EXCEPTION_MESSAGE);
        }

        // Vérifier si la ligne pouleuse est valide,
        // Déclencher une exception indiquant que la ligne est incorrêcte.
        if(!Pattern.matches(PatternConstantes.TONDEUSE_COMMANDES_PATTERN, ligne))
        {
            throw new ParseLigneException(ErreursMessages.LIGNE_TONDEUSE_COMMANDES_EXCEPTION_MESSAGE);
        }
        // Créer une instance de la classe {@link TondeuseCommandes}
        var tondeuseCommandes = new TondeuseCommandes();
        Arrays.stream(ligne.split(StringUtils.EMPTY))
                .forEach(element ->
                        tondeuseCommandes.addCommande(Commande.getCommandeEnumByCommande(element)));
        return tondeuseCommandes;
    }

    /**
     * @see ParseurLigne#getClassTraite()
     */
    @Override
    public Class getClassTraite()
    {
        return TondeuseCommandes.class;
    }
}