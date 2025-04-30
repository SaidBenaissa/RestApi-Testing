package no.skatteetaten.grunnlag.service;

import org.springframework.stereotype.Service;

import no.skatteetaten.grunnlag.model.GrunnlagRequest;

@Service
public class GrunnlagService {
    public boolean validerGrunnlag(GrunnlagRequest foresporsel) {
        int sumSaldo = foresporsel.getOppgave().stream().mapToInt(GrunnlagRequest.Oppgave::getSaldo).sum();
        int sumAksjeandel = foresporsel.getOppgave().stream().mapToInt(GrunnlagRequest.Oppgave::getAksjeandel).sum();
        return sumSaldo == foresporsel.getOppgaveoppsummering().getSumSaldo() &&
               sumAksjeandel == foresporsel.getOppgaveoppsummering().getSumAksjehandel();
    }
}