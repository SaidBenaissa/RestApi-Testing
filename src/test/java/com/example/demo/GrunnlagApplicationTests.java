package com.example.demo;

import com.example.demo.model.GrunnlagRequest;
import com.example.demo.service.GrunnlagService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = GrunnlagApplication.class)
class GrunnlagApplicationTests {

    @Autowired
    private GrunnlagService grunnlagService;

    @Test
    void testGyldigGrunnlag() {
        GrunnlagRequest foresporsel = new GrunnlagRequest();
        foresporsel.setOppgave(new ArrayList<>());
        foresporsel.setOppgaveoppsummering(new GrunnlagRequest.OppgaveOppsummering());
        // Populate foresporsel with valid data
        boolean erGyldig = grunnlagService.validerGrunnlag(foresporsel);
        assertTrue(erGyldig);
    }

    @Test
    void testUgyldigGrunnlag() {
        GrunnlagRequest foresporsel = new GrunnlagRequest();
        foresporsel.setOppgave(new ArrayList<>());
        foresporsel.setOppgaveoppsummering(new GrunnlagRequest.OppgaveOppsummering());
        // Populate foresporsel with invalid data
        boolean erGyldig = grunnlagService.validerGrunnlag(foresporsel);
        assertFalse(erGyldig);
    }

    @Test
    void testEndepunkt() {
        RestTemplate restTemplate = new RestTemplate();
        GrunnlagRequest foresporsel = new GrunnlagRequest();
        foresporsel.setOppgave(new ArrayList<>());
        foresporsel.setOppgaveoppsummering(new GrunnlagRequest.OppgaveOppsummering());
        // Populate foresporsel with valid data
        ResponseEntity<String> respons = restTemplate.postForEntity("http://localhost:8080/grunnlag", foresporsel, String.class);
        assertEquals(HttpStatus.OK, respons.getStatusCode());
    }
}
