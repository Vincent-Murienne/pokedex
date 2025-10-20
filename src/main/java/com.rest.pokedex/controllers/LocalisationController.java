package com.rest.pokedex.controllers;

import com.rest.pokedex.entities.Localisation;
import com.rest.pokedex.services.LocalisationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/localisations")
public class LocalisationController {

    private final LocalisationService localisationService;

    public LocalisationController(LocalisationService localisationService) {
        this.localisationService = localisationService;
    }

    @GetMapping
    public List<Localisation> getAll() {
        return localisationService.findAllLocalisations();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Localisation> getById(@PathVariable UUID id) {
        return localisationService.findLocalisationById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Localisation create(@RequestBody Localisation localisation) {
        return localisationService.saveLocalisation(localisation);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable UUID id) {
        localisationService.deleteLocalisationById(id);
    }
}
