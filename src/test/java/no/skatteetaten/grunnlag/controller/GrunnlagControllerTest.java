package no.skatteetaten.grunnlag.controller;

import no.skatteetaten.grunnlag.model.GrunnlagRequest;
import no.skatteetaten.grunnlag.service.GrunnlagService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class GrunnlagControllerTest {

    @Mock
    private GrunnlagService grunnlagService;

    @InjectMocks
    private GrunnlagController grunnlagController;

    public GrunnlagControllerTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testBehandleGrunnlagValid() {
        GrunnlagRequest request = new GrunnlagRequest();
        when(grunnlagService.validerGrunnlag(request)).thenReturn(true);

        ResponseEntity<String> response = grunnlagController.behandleGrunnlag(request);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals("Gyldige data", response.getBody());
    }

    @Test
    void testBehandleGrunnlagInvalid() {
        GrunnlagRequest request = new GrunnlagRequest();
        when(grunnlagService.validerGrunnlag(request)).thenReturn(false);

        ResponseEntity<String> response = grunnlagController.behandleGrunnlag(request);

        assertEquals(400, response.getStatusCodeValue());
        assertEquals("Ugyldige data", response.getBody());
    }
}