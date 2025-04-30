package no.skatteetaten.grunnlag.model;

import jakarta.validation.constraints.NotNull;
import java.util.List;

public class GrunnlagRequest {
    @NotNull
    private Innsender innsender;
    @NotNull
    private List<Oppgave> oppgave;
    @NotNull
    private OppgaveOppsummering oppgaveoppsummering;
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
    public void setInnsender(Innsender innsender) {
        this.innsender = innsender;
    }
    public static class Innsender {
        @NotNull
        private String navn;
        @NotNull
        private String foedselsnummer;
        public String getNavn() {
            return navn;
        }
        public String getFoedselsnummer() {
            return foedselsnummer;
        }

        public void setNavn(String navn) {
            this.navn = navn;
        }

        public void setFoedselsnummer(String foedselsnummer) {
            this.foedselsnummer = foedselsnummer;
        }
    }
    public static class Oppgave {
        @NotNull
        private Integer saldo;
        @NotNull
        private Integer aksjeandel;
        public Integer getSaldo() {
            return saldo;
        }
        public Integer getAksjeandel() {
            return aksjeandel;
        }
        public void setAksjeandel(int aksjeandel) {
            this.aksjeandel = aksjeandel;
        }
        public void setSaldo(int saldo) {
            this.saldo = saldo;
        }
    }
    public static class OppgaveOppsummering {
        @NotNull
        private Integer sumSaldo;
        @NotNull
        private Integer sumAksjehandel;
        public Integer getSumSaldo() {
            return sumSaldo;
        }
        public Integer getSumAksjehandel() {
            return sumAksjehandel;
        }
        public void setSumSaldo(int sumSaldo) {
            this.sumSaldo = sumSaldo;
        }
        public void setSumAksjehandel(int sumAksjehandel) {
            this.sumAksjehandel = sumAksjehandel;
        }
    }
}