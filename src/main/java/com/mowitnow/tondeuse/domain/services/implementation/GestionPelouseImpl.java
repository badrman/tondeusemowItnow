package com.mowitnow.tondeuse.domain.services.implementation;

import com.mowitnow.tondeuse.domain.model.Pelouse;
import com.mowitnow.tondeuse.domain.model.Tondeuse;
import com.mowitnow.tondeuse.domain.model.TondeuseCommandes;
import com.mowitnow.tondeuse.domain.services.GestionPelouse;
import com.mowitnow.tondeuse.domain.services.GestionTondeuse;
import com.mowitnow.tondeuse.domain.services.ParseurLigne;
import lombok.Getter;
import lombok.Setter;

import java.io.BufferedReader;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

/**
 * @author berrami badr
 * @since 0.0.1-SNAPSHOT
 * Description : Une implémentation de l'interface {@link GestionPelouse} permettant
 *               de gérer une pelouse en lançant l'exécution des tondeuses.
 */
@Setter
@Getter
public class GestionPelouseImpl implements GestionPelouse
{
    /** La liste des implementations de service {@link ParseurLigne} **/
    private List<ParseurLigne> parseurLignes;

    /** Service de gestion de tondeuse **/
    private GestionTondeuse gestionTondeuse;

    /**
     * Constructeur pour créer une instance de service {@link GestionPelouseImpl}
     * @param parseurLignes : la liste des implémentations de type {@link ParseurLigne}
     * @param gestionTondeuse : une instance de type {@link GestionTondeuse}
     */
    public GestionPelouseImpl(List<ParseurLigne> parseurLignes, GestionTondeuse gestionTondeuse)
    {
        this.parseurLignes = parseurLignes;
        this.gestionTondeuse = gestionTondeuse;
    }

    /**
     * @see GestionPelouse#intialiserEtLancerTendeuse(BufferedReader)
     */
    @Override
    public Pelouse intialiserEtLancerTendeuse(BufferedReader fichierEntree) {
        var pelouse = initialiserPelouse(fichierEntree);
        if (Objects.nonNull(pelouse))
        {
            demarrerLestendeuses(pelouse);
        }
        return pelouse;
    }
    /**
     * @see GestionPelouse#initialiserPelouse(BufferedReader)
     */
    @Override
    public Pelouse initialiserPelouse(BufferedReader fichierEntree)
    {
        Pelouse pelouse = null;
        try (var scanner = new Scanner(fichierEntree))
        {
            boolean premiereLigne = true;
            while (scanner.hasNext())
            {
                if (premiereLigne)
                {
                    pelouse = parserLigneDePelouse(scanner.nextLine());
                    premiereLigne = false;
                }
                if (Objects.nonNull(pelouse)) {
                    pelouse.ajouterTondeuse(parserTondeuseInformations(scanner));
                }
            }
        }
        return pelouse;
    }

    /**
     * Déclencher l'exécution des tondeuses
     * @param pelouse : une instance de type {@link Pelouse}
     */
    private void demarrerLestendeuses(Pelouse pelouse)
    {
        pelouse.getTondeuses().stream()
                .forEach(tondeuse -> gestionTondeuse.demarrerTondeuse(tondeuse, pelouse.getDimensionPelouse()));
    }

    /**
     * Cette méthode permet de parser une ligne des coordonnées de pelouse
     * @param coordonneesPelouse : les coordonnées de pelouse
     * @return une instance de type {@link Pelouse}
     */
    private Pelouse parserLigneDePelouse(String coordonneesPelouse)
    {
        Pelouse pelouse = null;
        var pelouseService = getParseurLigneService(Pelouse.class);
        if (Objects.nonNull(pelouseService))
        {
            pelouse = (Pelouse) pelouseService.lireLigneDeFichier(coordonneesPelouse);
        }
        return pelouse;
    }

    /**
     * Cette méthode permet de parser les coordonnées et les commandes d'une tondeuse
     * @param scanner : un scanner pour lire les coordonnées et les commandes d'une tondeuse
     * @return une instance de type {@link Tondeuse}
     */
    private Tondeuse parserTondeuseInformations(Scanner scanner)
    {
        Tondeuse tondeuse = null;
        // Lire la premiere ligne : les coordonnées de la tondeuse
        var tondeuseParseurService = getParseurLigneService(Tondeuse.class);
        if (Objects.nonNull(tondeuseParseurService))
        {
            tondeuse = (Tondeuse) tondeuseParseurService.lireLigneDeFichier(scanner.nextLine());
            // Ensuite, récupérer les commandes à éxecuter pour la tondeuse en cours.
            if (Objects.nonNull(tondeuse))
            {
                tondeuse.setCommandes(parserTondeuseCommandes(scanner));
            }
        }
        return tondeuse;
    }

    /**
     * Cette méthode permet de parser les commandes d'une tondeuse
     * @param scanner : un scanner pour lire les commandes d'une tondeuse
     * @return une instance de type {@link TondeuseCommandes}
     */
    private TondeuseCommandes parserTondeuseCommandes(Scanner scanner)
    {
        TondeuseCommandes tondeuseCommandes = null;
        var tondeuseCommandesParseurService = getParseurLigneService(TondeuseCommandes.class);
        if (Objects.nonNull(tondeuseCommandesParseurService))
        {
            tondeuseCommandes = (TondeuseCommandes) tondeuseCommandesParseurService.lireLigneDeFichier(scanner.nextLine());
        }
        return tondeuseCommandes;
    }

    /**
     * Méthode utilitaire pour récupérer le service de parsing correspond à l'element en entrée
     * @param parseurLigneClass : La classe correspondant à la ligne à parser
     * @return une implémentation de {@link ParseurLigne}
     */
    private ParseurLigne getParseurLigneService(Class<? extends Object> parseurLigneClass)
    {
        return parseurLignes.stream().filter(parseurLigne -> parseurLigneClass.equals(parseurLigne.getClassTraite()))
                .findFirst().orElse(null);
    }
}
