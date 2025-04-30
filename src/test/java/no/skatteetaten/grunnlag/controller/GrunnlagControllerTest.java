package no.skatteetaten.grunnlag.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class GrunnlagControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testValidRequest() throws Exception {
        String validJson = "{ \"innsender\": { \"navn\": \"Ole Olsen\", \"foedselsnummer\": \"26063643458\" }, \"oppgave\": [{ \"saldo\": 100, \"aksjeandel\": 50 }], \"oppgaveoppsummering\": { \"sumSaldo\": 100, \"sumAksjehandel\": 50 } }";

        mockMvc.perform(post("/grunnlag")
                .contentType("application/json")
                .content(validJson))
                .andExpect(status().isOk());
    }

    @Test
    void testInvalidRequest() throws Exception {
        String invalidJson = "{ \"innsender\": { \"navn\": \"Ole Olsen\", \"foedselsnummer\": \"26063643458\" }, \"oppgave\": [{ \"saldo\": 100, \"aksjeandel\": 50 }], \"oppgaveoppsummering\": { \"sumSaldo\": 200, \"sumAksjehandel\": 100 } }";

        mockMvc.perform(post("/grunnlag")
                .contentType("application/json")
                .content(invalidJson))
                .andExpect(status().isBadRequest());
    }
}