package com.example.demo.controller;

import com.example.demo.model.GrunnlagRequest;
import com.example.demo.service.GrunnlagService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
}