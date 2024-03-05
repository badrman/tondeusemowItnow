package com.mowitnow.tondeuse.domain.services;

import com.mowitnow.tondeuse.domain.constantes.ErreursMessages;
import com.mowitnow.tondeuse.domain.constantes.TestConstantes;
import com.mowitnow.tondeuse.domain.exception.ParseLigneException;
import com.mowitnow.tondeuse.domain.model.Commande;
import com.mowitnow.tondeuse.domain.model.TondeuseCommandes;
import com.mowitnow.tondeuse.domain.services.implementation.ParseurLigneCommandesTondeuseImpl;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * @author berrami badr
 * @since 0.0.1-SNAPSHOT
 * Description : Classe de test regroupant les tests de service {@link com.mowitnow.tondeuse.domain.services.implementation.ParseurLigneCommandesTondeuseImpl}
 */
class ParseurLigneCommandesTondeuseTest
{
    private static ParseurLigne parseurLigneCommandesTondeuse;

    @BeforeAll
    public static void setUp()
    {
        parseurLigneCommandesTondeuse = new ParseurLigneCommandesTondeuseImpl();
    }

    @Test
    void lecture_ligne_des_commandes_de_tondeuse_valide()
    {
        // Arrange
        // -- Ligne contenant les commandes de tendeuse
        var tondeuseCoordonnees = "GAGAA";
        // -- Objet Commande Attendu (Enveloppe)
        var tondeuseCommandesAttendu = new TondeuseCommandes();
        // -- Liste des commandes
        var commandes = new ArrayList<Commande>();
        // -- "G", puis "A", "G", "A" et "A"
        commandes.add(Commande.GAUCHE);
        commandes.add(Commande.AVANCER);
        commandes.add(Commande.GAUCHE);
        commandes.add(Commande.AVANCER);
        commandes.add(Commande.AVANCER);
        tondeuseCommandesAttendu.setCommandes(commandes);
        // Act
        var tondeuseCommandesObtenu = (TondeuseCommandes) parseurLigneCommandesTondeuse.lireLigneDeFichier(tondeuseCoordonnees);
        // Assert
        Assertions.assertEquals(tondeuseCommandesAttendu, tondeuseCommandesObtenu);
    }

    @Test
    void lecture_ligne_des_commandes_de_tendeuse_vide()
    {
        // Arrange
        // -- Ligne des commandes "VIDE"
        var  tondeuseCoordonneesVide = StringUtils.EMPTY;
        // Act
        var parseLigneException = assertThrows(ParseLigneException.class,
                () -> parseurLigneCommandesTondeuse.lireLigneDeFichier(tondeuseCoordonneesVide),
                TestConstantes.MESSAGE_APPEL_METHODE_LIRE_LIGNE_DE_FICHIER);
        assertEquals(ErreursMessages.LIGNE_TONDEUSE_COMMANDES_VIDE_EXCEPTION_MESSAGE, parseLigneException.getMessage());
    }

    @Test
    void lecture_ligne_des_commandes_de_tendeuse_incorrecte()
    {
        // Arrange
        // -- Ligne des commandes "VIDE"
        var  tondeuseCoordonneesIncorrecte = "GAGAF";
        // Act
        var parseLigneException = assertThrows(ParseLigneException.class,
                () -> parseurLigneCommandesTondeuse.lireLigneDeFichier(tondeuseCoordonneesIncorrecte),
                TestConstantes.MESSAGE_APPEL_METHODE_LIRE_LIGNE_DE_FICHIER);
        assertEquals(ErreursMessages.LIGNE_TONDEUSE_COMMANDES_EXCEPTION_MESSAGE, parseLigneException.getMessage());
    }
}
