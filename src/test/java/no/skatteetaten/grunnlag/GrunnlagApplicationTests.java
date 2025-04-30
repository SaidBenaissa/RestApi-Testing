package no.skatteetaten.grunnlag;

import no.skatteetaten.grunnlag.model.GrunnlagRequest;
import no.skatteetaten.grunnlag.service.GrunnlagService;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = GrunnlagApplication.class)
@AutoConfigureMockMvc
class GrunnlagApplicationTests {
    @Autowired
    private GrunnlagService grunnlagService;
    @Autowired
    private MockMvc mockMvc;
    private static final String VALID_JSON_REQUEST = "{" +
            "\"innsender\": {\"navn\": \"Ole Olsen\", \"foedselsnummer\": \"26063643458\"}," +
            "\"oppgave\": [" +
            "{\"saldo\": 100, \"aksjeandel\": 200}," +
            "{\"saldo\": 110, \"aksjeandel\": 210}" +
            "]," +
            "\"oppgaveoppsummering\": {\"sumSaldo\": 210, \"sumAksjehandel\": 410}" +
            "}";
    private static final String INVALID_JSON_REQUEST = "{" +
            "\"innsender\": {\"navn\": \"Ole Olsen\", \"foedselsnummer\": \"26063643458\"}," +
            "\"oppgave\": [" +
            "{\"saldo\": 100, \"aksjeandel\": 200}," +
            "{\"saldo\": 110, \"aksjeandel\": 210}" +
            "]," +
            "\"oppgaveoppsummering\": {\"sumSaldo\": 300, \"sumAksjehandel\": 500}" +
            "}";
    @Test
    void testGyldigGrunnlag() {
        GrunnlagRequest foresporsel = new GrunnlagRequest();
        List<GrunnlagRequest.Oppgave> oppgaver = new ArrayList<>();
        GrunnlagRequest.Oppgave oppgave = new GrunnlagRequest.Oppgave();
        oppgave.setSaldo(100);
        oppgave.setAksjeandel(50);
        oppgaver.add(oppgave);
        foresporsel.setOppgave(oppgaver);
        GrunnlagRequest.OppgaveOppsummering oppsummering = new GrunnlagRequest.OppgaveOppsummering();
        oppsummering.setSumSaldo(100);
        oppsummering.setSumAksjehandel(50);
        foresporsel.setOppgaveoppsummering(oppsummering);
        boolean erGyldig = grunnlagService.validerGrunnlag(foresporsel);
        assertTrue(erGyldig);
    }
    @Test
    void testUgyldigGrunnlag() {
        GrunnlagRequest foresporsel = new GrunnlagRequest();
        List<GrunnlagRequest.Oppgave> oppgaver = new ArrayList<>();
        GrunnlagRequest.Oppgave oppgave = new GrunnlagRequest.Oppgave();
        oppgave.setSaldo(100);
        oppgave.setAksjeandel(50);
        oppgaver.add(oppgave);
        foresporsel.setOppgave(oppgaver);
        GrunnlagRequest.OppgaveOppsummering oppsummering = new GrunnlagRequest.OppgaveOppsummering();
        oppsummering.setSumSaldo(200);
        oppsummering.setSumAksjehandel(100);
        foresporsel.setOppgaveoppsummering(oppsummering);
        boolean erGyldig = grunnlagService.validerGrunnlag(foresporsel);
        assertFalse(erGyldig);
    }
    @Test
    void testEndepunkt() throws Exception {
        GrunnlagRequest foresporsel = new GrunnlagRequest();
        List<GrunnlagRequest.Oppgave> oppgaver = new ArrayList<>();
        GrunnlagRequest.Oppgave oppgave = new GrunnlagRequest.Oppgave();
        oppgave.setSaldo(100);
        oppgave.setAksjeandel(50);
        oppgaver.add(oppgave);
        foresporsel.setOppgave(oppgaver);
        GrunnlagRequest.OppgaveOppsummering oppsummering = new GrunnlagRequest.OppgaveOppsummering();
        oppsummering.setSumSaldo(100);
        oppsummering.setSumAksjehandel(50);
        foresporsel.setOppgaveoppsummering(oppsummering);
        mockMvc.perform(post("/grunnlag")
                .contentType("application/json")
                .content(VALID_JSON_REQUEST))
                .andExpect(status().isOk());
    }
    @Test
    void testInvalidJsonRequest() throws Exception {
        mockMvc.perform(post("/grunnlag")
                .contentType("application/json")
                .content(INVALID_JSON_REQUEST))
                .andExpect(status().isBadRequest());
    }
}
