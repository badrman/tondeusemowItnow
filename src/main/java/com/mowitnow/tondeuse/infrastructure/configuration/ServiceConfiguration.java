package com.mowitnow.tondeuse.infrastructure.configuration;

import com.mowitnow.tondeuse.domain.services.GestionPelouse;
import com.mowitnow.tondeuse.domain.services.GestionTondeuse;
import com.mowitnow.tondeuse.domain.services.GestionTondeuseCommande;
import com.mowitnow.tondeuse.domain.services.ParseurLigne;
import com.mowitnow.tondeuse.domain.services.implementation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;


/**
 * @author berrami badr
 * @since 0.0.1-SNAPSHOT
 * Description : Une classe de configuration Spring, utilisée pour définir les Beans utilisées au niveau de l'application
 */
@Configuration
public class ServiceConfiguration {

    @Bean
    public ParseurLigne parseurlignecommandestondeuse()
    {
        return new ParseurLigneCommandesTondeuseImpl();
    }

    @Bean
    public ParseurLigne parseurlignecoordonneestondeuse()
    {
        return new ParseurLigneCoordonneesTondeuseImpl();
    }

    @Bean
    public ParseurLigne parseurlignepelouse()
    {
        return new ParseurLignePelouseImpl();
    }

    @Bean
    public GestionPelouse gestionpelouse(@Autowired List<ParseurLigne> parseurLignes, @Autowired GestionTondeuse gestionTondeuse)
    {
        return new GestionPelouseImpl(parseurLignes, gestionTondeuse);
    }

    @Bean
    public GestionTondeuse gestiontondeuse(@Autowired GestionTondeuseCommande gestionTondeuseCommande)
    {
        return new GestionTondeuseImpl(gestionTondeuseCommande);
    }

    @Bean
    public GestionTondeuseCommande gestiontondeusecommande()
    {
        return new GestionTondeuseCommandeImpl();
    }
}
