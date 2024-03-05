package com.mowitnow.tondeuse.domain.services;

import com.mowitnow.tondeuse.domain.model.Commande;
import com.mowitnow.tondeuse.domain.model.Direction;
import com.mowitnow.tondeuse.domain.model.Position;
import com.mowitnow.tondeuse.domain.model.Tondeuse;
import com.mowitnow.tondeuse.domain.model.TondeuseCommandes;
import com.mowitnow.tondeuse.domain.services.implementation.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author berrami badr
 * @since 0.0.1-SNAPSHOT
 * Description : Classe de test regroupant les tests de service {@link GestionTondeuseCommande}
 */
class GestionTondeuseCommandeTest {

    private static GestionTondeuseCommande gestionTondeuseCommande;

    @BeforeAll
    public static void setUp()
    {
        // Créer une liste des services de mapping
        gestionTondeuseCommande = new GestionTondeuseCommandeImpl();
    }

    @Test
    void pivoter_tondeuse_a_gauche()
    {
        // Arrange
        // - Préparer la tondeuse exemple
        var position = creerPosition(2, 2);
        var tondeuseCommandes = creerTondeuseCommandes(Commande.GAUCHE, Commande.AVANCER, Commande.AVANCER);
        var tondeuseInitiale = creerTondeuse(position, Direction.NORTH, tondeuseCommandes);
        // - Préparer la tondeuse attendu
        var position2 = creerPosition(2, 2);
        var tondeuseAttendu = new Tondeuse(position2, Direction.WEST, tondeuseCommandes);
        // - Position maximale (coin superieur à droite de la pelouse)
        var positionMaximale = creerPosition(3, 3);
        // Act
        gestionTondeuseCommande.executerLaCommande(Commande.GAUCHE, tondeuseInitiale, positionMaximale);
        // Assert
        assertEquals(tondeuseAttendu, tondeuseInitiale);
    }

    @Test
    void pivoter_tondeuse_a_droite()
    {
        // Arrange
        // - Préparer la tondeuse exemple
        var position = creerPosition(2, 2);
        var tondeuseCommandes = creerTondeuseCommandes(Commande.DROITE, Commande.AVANCER, Commande.DROITE);
        var tondeuseInitiale = creerTondeuse(position, Direction.NORTH, tondeuseCommandes);
        // - Préparer la tondeuse attendu
        var position2 = creerPosition(2, 2);
        var tondeuseAttendu = new Tondeuse(position2, Direction.EAST, tondeuseCommandes);
        // - Position maximale (coin superieur à droite de la pelouse)
        var positionMaximale = creerPosition(3, 3);
        // Act
        gestionTondeuseCommande.executerLaCommande(Commande.DROITE, tondeuseInitiale, positionMaximale);
        // Assert
        assertEquals(tondeuseAttendu, tondeuseInitiale);
    }

    @Test
    void pivoter_tondeuse_a_gauche_et_avancer_un_pas()
    {
        // Arrange
        // - Préparer la tondeuse exemple
        var position = creerPosition(1, 3);
        var tondeuseCommandes = creerTondeuseCommandes(Commande.GAUCHE, Commande.AVANCER, Commande.AVANCER);
        var tondeuseInitiale = creerTondeuse(position, Direction.NORTH, tondeuseCommandes);
        // - Préparer la tondeuse attendu
        var position2 = creerPosition(0, 3);
        var tondeuseAttendu = new Tondeuse(position2, Direction.WEST, tondeuseCommandes);
        // - Position maximale (coin superieur à droite de la pelouse)
        var positionMaximale = creerPosition(3, 3);
        // Act
        // - Pivoter la tondeuse à gauche
        gestionTondeuseCommande.executerLaCommande(Commande.GAUCHE, tondeuseInitiale, positionMaximale);
        // - Avancer la tondeuse dans la pelouse
        gestionTondeuseCommande.executerLaCommande(Commande.AVANCER, tondeuseInitiale, positionMaximale);
        // Assert
        assertEquals(tondeuseAttendu, tondeuseInitiale);
    }

    @Test
    void pivoter_tondeuse_a_droite_et_avancer_un_pas()
    {
        // Arrange
        // - Préparer la tondeuse exemple
        var position = creerPosition(0, 1);
        var tondeuseCommandes = creerTondeuseCommandes(Commande.DROITE, Commande.AVANCER, Commande.DROITE);
        var tondeuseInitiale = creerTondeuse(position, Direction.NORTH, tondeuseCommandes);
        // - Préparer la tondeuse attendu
        var position2 = creerPosition(1, 1);
        var tondeuseAttendu = new Tondeuse(position2, Direction.EAST, tondeuseCommandes);
        // - Position maximale (coin superieur à droite de la pelouse)
        var positionMaximale = creerPosition(3, 3);
        // Act
        // - Pivoter la tondeuse à gauche
        gestionTondeuseCommande.executerLaCommande(Commande.DROITE, tondeuseInitiale, positionMaximale);
        // - Avancer la tondeuse dans la pelouse
        gestionTondeuseCommande.executerLaCommande(Commande.AVANCER, tondeuseInitiale, positionMaximale);
        // Assert
        assertEquals(tondeuseAttendu, tondeuseInitiale);
    }

    @Test
    void avancer_une_tondeuse_en_dehors_de_pelouse()
    {
        // Arrange
        // - Préparer la tondeuse exemple
        var position = creerPosition(3, 0);
        var tondeuseCommandes = creerTondeuseCommandes(Commande.DROITE, Commande.AVANCER, Commande.DROITE);
        var tondeuseInitiale = creerTondeuse(position, Direction.EAST, tondeuseCommandes);
        // - Préparer la tondeuse attendu
        var position2 = creerPosition(3, 0);
        var tondeuseAttendu = new Tondeuse(position2, Direction.SOUTH, tondeuseCommandes);
        // - Position maximale (coin superieur à droite de la pelouse)
        var positionMaximale = creerPosition(3, 3);
        // Act
        // - Pivoter la tondeuse à gauche
        gestionTondeuseCommande.executerLaCommande(Commande.DROITE, tondeuseInitiale, positionMaximale);
        // - Avancer la tondeuse dans la pelouse
        gestionTondeuseCommande.executerLaCommande(Commande.AVANCER, tondeuseInitiale, positionMaximale);
        // Assert
        // Pas de changement au niveau de la position
        assertEquals(tondeuseAttendu, tondeuseInitiale);
    }


    private Tondeuse creerTondeuse(Position positionInitiale, Direction direction, TondeuseCommandes tondeuseCommandes)
    {
        return new Tondeuse(positionInitiale, direction, tondeuseCommandes);
    }

    private Position creerPosition(Integer positionX, Integer positionY)
    {
        return new Position(positionX, positionY);
    }

    private TondeuseCommandes creerTondeuseCommandes(Commande... commandes)
    {
        var tondeuseCommandes = new TondeuseCommandes();
        Arrays.asList(commandes).stream().forEach(commande -> tondeuseCommandes.addCommande(commande));
        return tondeuseCommandes;
    }
}
