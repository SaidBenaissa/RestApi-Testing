package com.example.demo.service;

import com.example.demo.model.GrunnlagRequest;
import org.springframework.stereotype.Service;

@Service
public class GrunnlagService {

    public boolean validerGrunnlag(GrunnlagRequest foresporsel) {
        int sumSaldo = foresporsel.getOppgave().stream().mapToInt(GrunnlagRequest.Oppgave::getSaldo).sum();
        int sumAksjeandel = foresporsel.getOppgave().stream().mapToInt(GrunnlagRequest.Oppgave::getAksjeandel).sum();

        return sumSaldo == foresporsel.getOppgaveoppsummering().getSumSaldo() &&
               sumAksjeandel == foresporsel.getOppgaveoppsummering().getSumAksjehandel();
    }
}