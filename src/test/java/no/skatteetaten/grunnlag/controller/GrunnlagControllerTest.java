package no.skatteetaten.grunnlag.controller;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.nio.file.Files;
import java.nio.file.Paths;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class GrunnlagControllerTest {
    private static final Logger logger = LoggerFactory.getLogger(GrunnlagControllerTest.class);

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testValidRequest() throws Exception {
        String validJson = readJsonFile("json/request.json");

        logger.info("Sending valid JSON payload: {}", validJson);

        mockMvc.perform(post("/grunnlag")
                .contentType("application/json")
                .content(validJson))
                .andDo(result -> logger.info("Received response: {}", result.getResponse().getContentAsString()))
                .andExpect(status().isOk());
    }

    @Test
    void testInvalidRequestMalformed() throws Exception {
        String malformedJson = readJsonFile("json/schema.json");

        logger.info("Sending invalid JSON payload (schema): {}", malformedJson);

        mockMvc.perform(post("/grunnlag")
                .contentType("application/json")
                .content(malformedJson))
                .andDo(result -> logger.info("Received response: {}", result.getResponse().getContentAsString()))
                .andExpect(status().isBadRequest());
    }

    private String readJsonFile(String filePath) throws Exception {
        return new String(Files.readAllBytes(Paths.get("src/main/resources/" + filePath)));
    }
}