package com.mowitnow.tondeuse.application.utils;

import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import java.io.FileNotFoundException;
import java.nio.charset.StandardCharsets;


/**
 * @author berrami badr
 * @since 0.0.1-SNAPSHOT
 * Description : Classe utilitaire pour créer un exemple de fichier d'entree
 */
public class TraitementUtils
{
    public static MockMultipartFile recupererFichierEntree()
    {
        // - Récupérer le fichier
        var contenuFichier = "5 5\n" +
                "1 2 N\n" +
                "GAGAGAGAA\n" +
                "3 3 E\n" +
                "AADAADADDA";
         return new MockMultipartFile(
                "fichier",
                "fichierentree.txt",
                MediaType.TEXT_PLAIN_VALUE,
                contenuFichier.getBytes(StandardCharsets.UTF_8)
        );
    }
}
