package com.mowitnow.tondeuse.infrastructure.configuration;

import com.mowitnow.tondeuse.domain.services.GestionPelouse;
import com.mowitnow.tondeuse.domain.services.GestionTondeuse;
import com.mowitnow.tondeuse.domain.services.GestionTondeuseCommande;
import com.mowitnow.tondeuse.domain.services.ParseurLigne;
import com.mowitnow.tondeuse.domain.services.implementation.GestionPelouseImpl;
import com.mowitnow.tondeuse.domain.services.implementation.GestionTondeuseCommandeImpl;
import com.mowitnow.tondeuse.domain.services.implementation.GestionTondeuseImpl;
import com.mowitnow.tondeuse.domain.services.implementation.ParseurLigneCommandesTondeuseImpl;
import com.mowitnow.tondeuse.domain.services.implementation.ParseurLigneCoordonneesTondeuseImpl;
import com.mowitnow.tondeuse.domain.services.implementation.ParseurLignePelouseImpl;
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
    public ParseurLigne parseurLigneCommandesTondeuse()
    {
        return new ParseurLigneCommandesTondeuseImpl();
    }

    @Bean
    public ParseurLigne parseurLigneCoordonneesTondeuse()
    {
        return new ParseurLigneCoordonneesTondeuseImpl();
    }

    @Bean
    public ParseurLigne parseurLignePelouse()
    {
        return new ParseurLignePelouseImpl();
    }

    @Bean
    public GestionPelouse gestionPelouse(@Autowired List<ParseurLigne> parseurLignes, @Autowired GestionTondeuse gestionTondeuse)
    {
        return new GestionPelouseImpl(parseurLignes, gestionTondeuse);
    }

    @Bean
    public GestionTondeuse gestionTondeuse(@Autowired GestionTondeuseCommande gestionTondeuseCommande)
    {
        return new GestionTondeuseImpl(gestionTondeuseCommande);
    }

    @Bean
    public GestionTondeuseCommande gestionTondeuseCommande()
    {
        return new GestionTondeuseCommandeImpl();
    }
}
