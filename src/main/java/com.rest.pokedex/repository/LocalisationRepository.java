package com.rest.pokedex.repository;

import com.rest.pokedex.entities.Localisation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface LocalisationRepository extends JpaRepository<Localisation, UUID> {
}
