package com.mowitnow.tondeuse.domain.services;

import com.mowitnow.tondeuse.domain.constantes.ErreursMessages;
import com.mowitnow.tondeuse.domain.constantes.TestConstantes;
import com.mowitnow.tondeuse.domain.exception.ParseLigneException;
import com.mowitnow.tondeuse.domain.model.Pelouse;
import com.mowitnow.tondeuse.domain.model.Position;
import com.mowitnow.tondeuse.domain.services.implementation.ParseurLigneCoordonneesTondeuseImpl;
import com.mowitnow.tondeuse.domain.services.implementation.ParseurLignePelouseImpl;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * @author berrami badr
 * @since 0.0.1-SNAPSHOT
 * Description : Classe de test regroupant les tests de service {@link com.mowitnow.tondeuse.domain.services.implementation.ParseurLignePelouseImpl}
 */
class ParseurLignePelouseTest
{
    private static ParseurLigne parseurlignepelouse;

    @BeforeAll
    public static void setUp()
    {
        parseurlignepelouse = new ParseurLignePelouseImpl();
    }

    @Test
    void lecture_ligne_des_coordonnees_de_pelouse_valide()
    {
        // Arrange
        // - Initialisation de la ligne de coordonnées de pelouse
        var coordonneesPelouse = "1 5";
        // - Création d'une instance de l'objet {link @Pelouse}
        // - Création de la demonsion de la pelouse : Représenté par une grille des cases
        //   On fournit les coordonnées de la position du coin supérieur à droit
        var positionSuperieure = new Position(1, 5);
        var pelouse = new Pelouse(positionSuperieure, null);
        // Act
        // - Appeller le service de parsing des coordonnées de pelouse
        var pelouseObtenu = parseurlignepelouse.lireLigneDeFichier(coordonneesPelouse);
        // Arrange
        assertEquals(pelouse, pelouseObtenu);
    }

    @Test
    void lecture_ligne_des_coordonnees_de_pelouse_vide()
    {
        // Arrange
        // - Ligne de coordonnées vide
        var coordonneesPelouseVide = StringUtils.EMPTY;
        // Act
        var parseException = assertThrows(ParseLigneException.class, () -> parseurlignepelouse.lireLigneDeFichier(coordonneesPelouseVide), TestConstantes.MESSAGE_APPEL_METHODE_LIRE_LIGNE_DE_FICHIER);
        // Arrange
        assertEquals(ErreursMessages.LIGNE_PELOUSE_COORDONNEES_VIDE_EXCEPTION_MESSAGE, parseException.getMessage());
    }

    @Test
    void lecture_ligne_des_coordonnees_de_pelouse_incorrecte()
    {
        // Arrange
        // - Ligne de coordonnées incorrecte
        var coordonneesPelouseIncorrectees = "1 2 3";
        // Act
        var parseException = assertThrows(ParseLigneException.class, () -> parseurlignepelouse.lireLigneDeFichier(coordonneesPelouseIncorrectees), TestConstantes.MESSAGE_APPEL_METHODE_LIRE_LIGNE_DE_FICHIER);
        // Arrange
        assertEquals(ErreursMessages.LIGNE_PELOUSE_COORDONNEES_EXCEPTION_MESSAGE, parseException.getMessage());
    }
}
