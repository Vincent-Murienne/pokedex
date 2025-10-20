package com.rest.pokedex.services;

import com.rest.pokedex.entities.Localisation;
import com.rest.pokedex.repository.LocalisationRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class LocalisationService {

    private final LocalisationRepository localisationRepository;

    public LocalisationService(LocalisationRepository localisationRepository) {
        this.localisationRepository = localisationRepository;
    }

    public List<Localisation> findAllLocalisations() {
        return localisationRepository.findAll();
    }

    public Optional<Localisation> findLocalisationById(UUID id) {
        return localisationRepository.findById(id);
    }

    public Localisation saveLocalisation(Localisation localisation) {
        return localisationRepository.save(localisation);
    }

    public void deleteLocalisationById(UUID id) {
        localisationRepository.deleteById(id);
    }
}