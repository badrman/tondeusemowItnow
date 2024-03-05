package com.mowitnow.tondeuse.domain.services;

import com.mowitnow.tondeuse.domain.model.Commande;
import com.mowitnow.tondeuse.domain.model.Direction;
import com.mowitnow.tondeuse.domain.model.Pelouse;
import com.mowitnow.tondeuse.domain.model.Position;
import com.mowitnow.tondeuse.domain.model.Tondeuse;
import com.mowitnow.tondeuse.domain.model.TondeuseCommandes;
import com.mowitnow.tondeuse.domain.services.implementation.GestionPelouseImpl;
import com.mowitnow.tondeuse.domain.services.implementation.GestionTondeuseCommandeImpl;
import com.mowitnow.tondeuse.domain.services.implementation.GestionTondeuseImpl;
import com.mowitnow.tondeuse.domain.services.implementation.ParseurLigneCommandesTondeuseImpl;
import com.mowitnow.tondeuse.domain.services.implementation.ParseurLigneCoordonneesTondeuseImpl;
import com.mowitnow.tondeuse.domain.services.implementation.ParseurLignePelouseImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertNull;

/**
 * @author berrami badr
 * @since 0.0.1-SNAPSHOT
 * Description : Classe de test regroupant les tests de service {@link GestionPelouse}
 */
class GestionPelouseTest
{
    private static GestionPelouse gestionpelouse;

    @BeforeAll
    public static void setUp()
    {
        // Créer une liste des services de mapping
        var parseLigneServices = Arrays.asList(new ParseurLignePelouseImpl(), new ParseurLigneCoordonneesTondeuseImpl(), new ParseurLigneCommandesTondeuseImpl());
        gestionpelouse = new GestionPelouseImpl(parseLigneServices, new GestionTondeuseImpl(new GestionTondeuseCommandeImpl()));
    }


    @Test
    void intialiser_pelouse_a_partir_dun_fichier() throws FileNotFoundException {
        // Arrange
        // - Initialiser le fichier d'entrée
        var fichierEntree = recupererFichierEntree("files/fichierentree.txt");
        //Position Initiale 1 2 + Direction NORTH
        var positionInitialeTendeuse1 = new Position(1, 2);
        //Position Initiale 3 3 + Direction EAST
        var positionInitialeTendeuse2 = new Position(3, 3);
        var pelouse = creerUnPelouse(positionInitialeTendeuse1, positionInitialeTendeuse2);
        // Act
        var pelouseObtenu = gestionpelouse.initialiserPelouse(fichierEntree);
        // Assert
        Assertions.assertEquals(pelouse, pelouseObtenu);
    }


    @Test
    void initialiser_pelouse_avec_fichier_vide() throws FileNotFoundException {
        // Arrange
        // - Déclarer un fichier vide
        var fichierVide = recupererFichierEntree("files/fichiervide.txt");
        // Act
        var pelouseNull = gestionpelouse.initialiserPelouse(fichierVide);
        // Assert
        assertNull(pelouseNull);
    }

    @Test
    void intialiser_et_demarrer_pelouse_a_partir_dun_fichier() throws FileNotFoundException {
        // Arrange
        // - Initialiser le fichier d'entrée
        var fichierEntree = recupererFichierEntree("files/fichierentree.txt");
        //Position Initiale 1 2 + Direction NORTH
        var positionInitialeTendeuse1 = new Position(1, 3);
        //Position Initiale 3 3 + Direction EAST
        var positionInitialeTendeuse2 = new Position(5, 1);
        var pelouse = creerUnPelouse(positionInitialeTendeuse1, positionInitialeTendeuse2);
        // Act
        var pelouseObtenu = gestionpelouse.intialiserEtLancerTendeuse(fichierEntree);
        // Assert
        Assertions.assertEquals(pelouse, pelouseObtenu);
    }

    private BufferedReader recupererFichierEntree(String nomFichier) throws FileNotFoundException {
        // - Récupérer le fichier
        var classLoader = getClass().getClassLoader();
        var fichierEntree = new File(classLoader.getResource(nomFichier).getFile());
        return new BufferedReader(new FileReader(fichierEntree));
    }

    private Pelouse creerUnPelouse(Position positionTondeuse1, Position positionTondeuse2)
    {
        // - Création de pelouse attendu
        // -- Dimension de pelouse
        var positionSuperieure = new Position(5,5);
        // -- Création des tondeuses
        // --- Tondeuse N1 (Position, Direction, TondeuseCommandes)
        // Commande GAGAGAGAA
        var commandes1 = new ArrayList<Commande>(Arrays.asList(Commande.GAUCHE, Commande.AVANCER, Commande.GAUCHE, Commande.AVANCER,
                Commande.GAUCHE, Commande.AVANCER, Commande.GAUCHE, Commande.AVANCER, Commande.AVANCER));

        var tondeuseCommandes1 = new TondeuseCommandes(commandes1);
        var tondeuse1 = new Tondeuse(positionTondeuse1, Direction.NORTH, tondeuseCommandes1);
        // --- Tondeuse N2 (Position, Direction, TondeuseCommandes)
        var commandes2 = new ArrayList<Commande>(Arrays.asList(Commande.AVANCER, Commande.AVANCER, Commande.DROITE, Commande.AVANCER,
                Commande.AVANCER, Commande.DROITE, Commande.AVANCER, Commande.DROITE, Commande.DROITE, Commande.AVANCER));
        var tondeuseCommandes2 = new TondeuseCommandes(commandes2);;
        var tondeuse2 = new Tondeuse(positionTondeuse2, Direction.EAST, tondeuseCommandes2);
        var pelouse = new Pelouse(positionSuperieure, Arrays.asList(tondeuse1, tondeuse2));
        return pelouse;
    }
}
