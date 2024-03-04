package com.mowitnow.tondeuse.application.controller;

import com.mowitnow.tondeuse.application.utils.TraitementUtils;
import com.mowitnow.tondeuse.domain.services.ParseurLigne;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


/**
 * @author berrami badr
 * @since 0.0.1-SNAPSHOT
 * Description : Classe de test pour le controller {@link com.mowitnow.tondeuse.application.controlleurs.TondeusesController}
 */
@SpringBootTest
@AutoConfigureMockMvc
class TondeusesControllerTest
{
    @Autowired
    private MockMvc mvc;

    @Test
    void demarrer_une_tendeuse() throws Exception
    {
        mvc.perform(multipart("/demarrerTondeuses")
                .file(TraitementUtils.recupererFichierEntree()))
                .andDo(print())
                .andExpect(status().isOk());
    }
}
