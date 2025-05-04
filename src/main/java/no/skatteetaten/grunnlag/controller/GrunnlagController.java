package no.skatteetaten.grunnlag.controller;

import jakarta.validation.Valid;
import no.skatteetaten.grunnlag.model.GrunnlagRequest;
import no.skatteetaten.grunnlag.service.GrunnlagService;

import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.StandardCharsets;

@RestController
@RequestMapping("/grunnlag")
public class GrunnlagController {
    private final GrunnlagService grunnlagService;
    public GrunnlagController(GrunnlagService grunnlagService) {
        this.grunnlagService = grunnlagService;
    }
    @PostMapping
    public ResponseEntity<String> behandleGrunnlag(@Valid @RequestBody GrunnlagRequest foresporsel) {
        boolean erGyldig = grunnlagService.validerGrunnlag(foresporsel);
        return erGyldig ? ResponseEntity.ok("Gyldige data") : ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Ugyldige data");
    }

    @GetMapping("/api/schema")
    public ResponseEntity<String> getSchema() {
        try {
            ClassPathResource resource = new ClassPathResource("json/schema.json");
            String schema = new String(resource.getInputStream().readAllBytes(), StandardCharsets.UTF_8);
            return ResponseEntity.ok(schema);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error loading schema.json");
        }
    }

    @GetMapping("/api/request")
    public ResponseEntity<String> getRequest() {
        try {
            ClassPathResource resource = new ClassPathResource("json/request.json");
            String request = new String(resource.getInputStream().readAllBytes(), StandardCharsets.UTF_8);
            return ResponseEntity.ok(request);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error loading request.json");
        }
    }
}