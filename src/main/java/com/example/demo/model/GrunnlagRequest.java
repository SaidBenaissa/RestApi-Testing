package com.example.demo.model;

import jakarta.validation.constraints.NotNull;
import java.util.List;

public class GrunnlagRequest {

    @NotNull
    private Innsender innsender;

    @NotNull
    private List<Oppgave> oppgave;

    @NotNull
    private OppgaveOppsummering oppgaveoppsummering;

    // Getters and Setters

    public Innsender getInnsender() {
        return innsender;
    }

    public List<Oppgave> getOppgave() {
        return oppgave;
    }

    public OppgaveOppsummering getOppgaveoppsummering() {
        return oppgaveoppsummering;
    }

    public void setOppgave(List<Oppgave> oppgave) {
        this.oppgave = oppgave;
    }

    public void setOppgaveoppsummering(OppgaveOppsummering oppgaveoppsummering) {
        this.oppgaveoppsummering = oppgaveoppsummering;
    }

    public static class Innsender {
        @NotNull
        private String navn;

        @NotNull
        private String foedselsnummer;

        // Getters and Setters

        public String getNavn() {
            return navn;
        }

        public String getFoedselsnummer() {
            return foedselsnummer;
        }
    }

    public static class Oppgave {
        @NotNull
        private Integer saldo;

        @NotNull
        private Integer aksjeandel;

        // Getters and Setters

        public Integer getSaldo() {
            return saldo;
        }

        public Integer getAksjeandel() {
            return aksjeandel;
        }
    }

    public static class OppgaveOppsummering {
        @NotNull
        private Integer sumSaldo;

        @NotNull
        private Integer sumAksjehandel;

        // Getters and Setters

        public Integer getSumSaldo() {
            return sumSaldo;
        }

        public Integer getSumAksjehandel() {
            return sumAksjehandel;
        }
    }
}