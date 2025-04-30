package no.skatteetaten.grunnlag.model;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;

class GrunnlagRequestTest {

    @Test
    void testInnsender() {
        GrunnlagRequest.Innsender innsender = new GrunnlagRequest.Innsender();
        innsender.setNavn("Ole Olsen");
        innsender.setFoedselsnummer("26063643458");

        assertEquals("Ole Olsen", innsender.getNavn());
        assertEquals("26063643458", innsender.getFoedselsnummer());
    }

    @Test
    void testOppgave() {
        GrunnlagRequest.Oppgave oppgave = new GrunnlagRequest.Oppgave();
        oppgave.setSaldo(100);
        oppgave.setAksjeandel(50);

        assertEquals(100, oppgave.getSaldo());
        assertEquals(50, oppgave.getAksjeandel());
    }

    @Test
    void testGrunnlagRequest() throws Exception {
        String jsonContent = new String(Files.readAllBytes(Paths.get("src/test/resources/json/request.json")));
        GrunnlagRequest request = new ObjectMapper().readValue(jsonContent, GrunnlagRequest.class);

        assertEquals("Ole Olsen", request.getInnsender().getNavn());
        assertEquals("26063643458", request.getInnsender().getFoedselsnummer());
        // Assertion: Expecting 2 elements in the oppgave list
        assertEquals(2, request.getOppgave().size());
        assertEquals(100, request.getOppgave().get(0).getSaldo());
        // Assertion: Expecting aksjeandel to be 200 for the first element
        assertEquals(200, request.getOppgave().get(0).getAksjeandel());
        // Assertion: Expecting sumSaldo to be 210
        assertEquals(210, request.getOppgaveoppsummering().getSumSaldo());
        // Assertion: Expecting sumAksjehandel to be 410
        assertEquals(410, request.getOppgaveoppsummering().getSumAksjehandel());
    }
}