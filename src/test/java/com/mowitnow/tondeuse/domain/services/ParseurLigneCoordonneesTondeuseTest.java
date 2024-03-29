package com.mowitnow.tondeuse.domain.services;

import com.mowitnow.tondeuse.domain.constantes.ErreursMessages;
import com.mowitnow.tondeuse.domain.constantes.TestConstantes;
import com.mowitnow.tondeuse.domain.exception.ParseLigneException;
import com.mowitnow.tondeuse.domain.model.Direction;
import com.mowitnow.tondeuse.domain.model.Position;
import com.mowitnow.tondeuse.domain.model.Tondeuse;
import com.mowitnow.tondeuse.domain.services.implementation.ParseurLigneCoordonneesTondeuseImpl;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * @author berrami badr
 * @since 0.0.1-SNAPSHOT
 * Description : Classe de test regroupant les tests de service {@link com.mowitnow.tondeuse.domain.services.implementation.ParseurLigneCoordonneesTondeuseImpl}
 */
class ParseurLigneCoordonneesTondeuseTest
{
    private static ParseurLigne parseurLigneCoordonneesTondeuse;

    @BeforeAll
    public static void setUp()
    {
        parseurLigneCoordonneesTondeuse = new ParseurLigneCoordonneesTondeuseImpl();
    }


    @Test
    void lecture_ligne_des_coordonnees_de_tondeuse_valide()
    {
        // Arrange
        // -- Ligne contenant les coordonnées de tendeuse
        var tondeuseCoordonnees = "1 3 N";
        // -- Objet Commande Attendu (Enveloppe)
        var tondeuseAttendu = new Tondeuse();
        // -- Objet Position (Coordonneés)
        var position = new Position(1, 3);
        // Initialiser la tondeuse
        tondeuseAttendu.setPosition(position);
        tondeuseAttendu.setDirection(Direction.NORTH);
        // Act
        var tondeuseObtenu = parseurLigneCoordonneesTondeuse.lireLigneDeFichier(tondeuseCoordonnees);
        // Arrange
        assertEquals(tondeuseAttendu, tondeuseObtenu);
    }

    @Test
    void lecture_ligne_des_coordonnees_de_tendeuse_vide()
    {
        // Arrange
        // -- Ligne des coordonnees "vide"
        var coordonneesTondeuseVide = StringUtils.EMPTY;
        // Act
        var parseLigneException = assertThrows(ParseLigneException.class,
                () -> parseurLigneCoordonneesTondeuse.lireLigneDeFichier(coordonneesTondeuseVide),
                TestConstantes.MESSAGE_APPEL_METHODE_LIRE_LIGNE_DE_FICHIER);
        assertEquals(ErreursMessages.LIGNE_TONDEUSE_COORDONNEES_VIDE_EXCEPTION_MESSAGE, parseLigneException.getMessage());
    }

    @Test
    void lecture_ligne_des_coordonnees_de_tendeuse_incorrecte()
    {
        // Arrange
        // -- Ligne des coordonnees "incorrecte"
        var coordonneesTondeuseIncorrecte = "1 3 F";
        // Act
        var parseLigneException = assertThrows(ParseLigneException.class,
                () -> parseurLigneCoordonneesTondeuse.lireLigneDeFichier(coordonneesTondeuseIncorrecte),
                TestConstantes.MESSAGE_APPEL_METHODE_LIRE_LIGNE_DE_FICHIER);
        assertEquals(ErreursMessages.LIGNE_TONDEUSE_COORDONNEES_EXCEPTION_MESSAGE, parseLigneException.getMessage());
    }
}
