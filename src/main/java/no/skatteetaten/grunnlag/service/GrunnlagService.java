package no.skatteetaten.grunnlag.service;

import no.skatteetaten.grunnlag.model.GrunnlagRequest;
import org.springframework.stereotype.Service;

@Service
public class GrunnlagService {
    public boolean validerGrunnlag(GrunnlagRequest foresporsel) {
        if (foresporsel == null || foresporsel.getOppgave() == null || foresporsel.getOppgaveoppsummering() == null) {
            return false;
        }

        int sumSaldo = 0;
        int sumAksjeandel = 0;

        for (GrunnlagRequest.Oppgave oppgave : foresporsel.getOppgave()) {
            if (oppgave.getSaldo() == null || oppgave.getAksjeandel() == null) {
                return false;
            }
            sumSaldo += oppgave.getSaldo();
            sumAksjeandel += oppgave.getAksjeandel();
        }

        GrunnlagRequest.OppgaveOppsummering oppsummering = foresporsel.getOppgaveoppsummering();
        if (oppsummering.getSumSaldo() == null || oppsummering.getSumAksjehandel() == null) {
            return false;
        }

        return sumSaldo == oppsummering.getSumSaldo() && sumAksjeandel == oppsummering.getSumAksjehandel();
    }
}