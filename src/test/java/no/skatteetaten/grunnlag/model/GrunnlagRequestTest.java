package no.skatteetaten.grunnlag.model;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

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
    void testOppgaveOppsummering() {
        GrunnlagRequest.OppgaveOppsummering oppsummering = new GrunnlagRequest.OppgaveOppsummering();
        oppsummering.setSumSaldo(210);
        oppsummering.setSumAksjehandel(410);

        assertEquals(210, oppsummering.getSumSaldo());
        assertEquals(410, oppsummering.getSumAksjehandel());
    }

    @Test
    void testGrunnlagRequest() {
        GrunnlagRequest request = new GrunnlagRequest();

        GrunnlagRequest.Innsender innsender = new GrunnlagRequest.Innsender();
        innsender.setNavn("Ole Olsen");
        innsender.setFoedselsnummer("26063643458");
        request.setInnsender(innsender);

        List<GrunnlagRequest.Oppgave> oppgaver = new ArrayList<>();
        GrunnlagRequest.Oppgave oppgave = new GrunnlagRequest.Oppgave();
        oppgave.setSaldo(100);
        oppgave.setAksjeandel(50);
        oppgaver.add(oppgave);
        request.setOppgave(oppgaver);

        GrunnlagRequest.OppgaveOppsummering oppsummering = new GrunnlagRequest.OppgaveOppsummering();
        oppsummering.setSumSaldo(100);
        oppsummering.setSumAksjehandel(50);
        request.setOppgaveoppsummering(oppsummering);

        assertEquals("Ole Olsen", request.getInnsender().getNavn());
        assertEquals("26063643458", request.getInnsender().getFoedselsnummer());
        assertEquals(1, request.getOppgave().size());
        assertEquals(100, request.getOppgave().get(0).getSaldo());
        assertEquals(50, request.getOppgave().get(0).getAksjeandel());
        assertEquals(100, request.getOppgaveoppsummering().getSumSaldo());
        assertEquals(50, request.getOppgaveoppsummering().getSumAksjehandel());
    }
}