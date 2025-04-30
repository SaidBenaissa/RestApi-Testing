package no.skatteetaten.grunnlag.service;

import no.skatteetaten.grunnlag.model.GrunnlagRequest;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GrunnlagServiceTest {

    private final GrunnlagService grunnlagService = new GrunnlagService();

    @Test
    void testValiderGrunnlagValid() {
        GrunnlagRequest request = new GrunnlagRequest();
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

        assertTrue(grunnlagService.validerGrunnlag(request));
    }

    @Test
    void testValiderGrunnlagInvalid() {
        GrunnlagRequest request = new GrunnlagRequest();
        List<GrunnlagRequest.Oppgave> oppgaver = new ArrayList<>();
        GrunnlagRequest.Oppgave oppgave = new GrunnlagRequest.Oppgave();
        oppgave.setSaldo(100);
        oppgave.setAksjeandel(50);
        oppgaver.add(oppgave);
        request.setOppgave(oppgaver);

        GrunnlagRequest.OppgaveOppsummering oppsummering = new GrunnlagRequest.OppgaveOppsummering();
        oppsummering.setSumSaldo(200);
        oppsummering.setSumAksjehandel(100);
        request.setOppgaveoppsummering(oppsummering);

        assertFalse(grunnlagService.validerGrunnlag(request));
    }
}