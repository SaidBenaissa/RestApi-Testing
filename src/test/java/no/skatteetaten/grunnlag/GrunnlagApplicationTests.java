package no.skatteetaten.grunnlag;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import no.skatteetaten.grunnlag.model.GrunnlagRequest;
import no.skatteetaten.grunnlag.service.GrunnlagService;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.nio.file.Files;
import java.nio.file.Paths;

@SpringBootTest(classes = GrunnlagApplication.class)
@AutoConfigureMockMvc
class GrunnlagApplicationTests {
    @Autowired
    private GrunnlagService grunnlagService;
    @Autowired
    private MockMvc mockMvc;

    @Test
    void testGyldigGrunnlag() throws Exception {
        String validJson = new String(Files.readAllBytes(Paths.get("src/test/resources/json/request.json")));
        GrunnlagRequest foresporsel = new ObjectMapper().readValue(validJson, GrunnlagRequest.class);

        boolean erGyldig = grunnlagService.validerGrunnlag(foresporsel);
        assertTrue(erGyldig);
    }

    @Test
    void testUgyldigGrunnlagMalformed() {
        try {
            String jsonContent = new String(Files.readAllBytes(Paths.get("src/test/resources/json/schema.json")));
            GrunnlagRequest request = new ObjectMapper().readValue(jsonContent, GrunnlagRequest.class);

            boolean erGyldig = grunnlagService.validerGrunnlag(request);
            assertFalse(erGyldig);
        } catch (Exception e) {
            fail("Unexpected exception: " + e.getMessage());
        }
    }

    @Test
    void testEndepunktOk() throws Exception {
        String validJson = new String(Files.readAllBytes(Paths.get("src/test/resources/json/request.json")));

        mockMvc.perform(post("/grunnlag")
                .contentType("application/json")
                .content(validJson))
                .andExpect(status().isOk());
    }

    @Test
    void testEndepunktBadRequestMalformed() throws Exception {
        String malformedJson = new String(Files.readAllBytes(Paths.get("src/test/resources/json/schema.json")));

        mockMvc.perform(post("/grunnlag")
                .contentType("application/json")
                .content(malformedJson))
                .andExpect(status().isBadRequest());
    }
}
