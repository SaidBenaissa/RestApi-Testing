package no.skatteetaten.grunnlag.service;

import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.databind.ObjectMapper;

import no.skatteetaten.grunnlag.model.GrunnlagRequest;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.nio.file.Files;
import java.nio.file.Paths;

class GrunnlagServiceTest {
    private final GrunnlagService grunnlagService = new GrunnlagService();
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void testValiderGrunnlagValid() throws Exception {
        String jsonContent = new String(Files.readAllBytes(Paths.get("src/test/resources/json/request.json")));
        GrunnlagRequest request = objectMapper.readValue(jsonContent, GrunnlagRequest.class);
        assertTrue(grunnlagService.validerGrunnlag(request));
    }

    @Test
    void testValiderGrunnlagMalformed() throws Exception {
        String jsonContent = new String(Files.readAllBytes(Paths.get("src/test/resources/json/schema.json")));
        try {
            GrunnlagRequest request = objectMapper.readValue(jsonContent, GrunnlagRequest.class);
            assertFalse(grunnlagService.validerGrunnlag(request));
        } catch (Exception e) {
            fail("Unexpected exception: " + e.getMessage());
        }
    }

    @Test
    void testValiderGrunnlagNullData() {
        GrunnlagRequest request = new GrunnlagRequest();
        assertFalse(grunnlagService.validerGrunnlag(request));
    }
}