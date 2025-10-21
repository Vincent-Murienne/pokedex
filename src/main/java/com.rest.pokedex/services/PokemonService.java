package com.rest.pokedex.services;

import com.rest.pokedex.entities.Pokemon;
import com.rest.pokedex.repository.PokemonRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PokemonService {

    private final PokemonRepository pokemonRepository;
    private final WeatherService weatherService;
    private static final Logger log = LoggerFactory.getLogger(PokemonService.class);

    public PokemonService(PokemonRepository pokemonRepository, WeatherService weatherService) {
        this.pokemonRepository = pokemonRepository;
        this.weatherService = weatherService;
    }

    public Optional<Pokemon> findPokemonById(UUID id) {
        return pokemonRepository.findById(id);
    }

    public List<Pokemon> findAllPokemons() {
        return pokemonRepository.findAll();
    }

    public List<Pokemon> findAllPokemonsWithWeather(String location) {
        String weather = weatherService.getWeather(location);
        log.info("Météo pour " + location + " : " + weather);

        List<Pokemon> allPokemons = pokemonRepository.findAll();
        log.info("Nombre de pokémons trouvés : " + allPokemons.size());

        if (weather != null && weather.toLowerCase().contains("rain")) {
            log.warn("Il pleut, les pokémons Feu sont désavantagés !");
            allPokemons.forEach(p -> {
                if (p.getTypes().stream().anyMatch(t -> "Feu".equalsIgnoreCase(t.getNom()))) {
                    int oldAttack = p.getAttack();
                    p.setAttack(p.getAttack() - 5);
                    log.warn(p.getNom() + " : attaque réduite de " + oldAttack + " → " + p.getAttack());
                }
            });
        } else {
            log.warn("Pas de pluie détectée, aucun malus appliqué.");
        }

        return allPokemons;
    }

    public Pokemon savePokemon(Pokemon pokemon) {
        return pokemonRepository.save(pokemon);
    }

    public Pokemon updatePokemon(UUID id, Pokemon updatedPokemon) {
        Pokemon existingPokemon = pokemonRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pokémon non trouvé avec l'id: " + id));

        if (updatedPokemon.getNom() != null) {
            existingPokemon.setNom(updatedPokemon.getNom());
        }
        if (updatedPokemon.getAttack() != 0) {
            existingPokemon.setAttack(updatedPokemon.getAttack());
        }
        if (updatedPokemon.getDefense() != 0) {
            existingPokemon.setDefense(updatedPokemon.getDefense());
        }
        if (updatedPokemon.getHp() != 0) {
            existingPokemon.setHp(updatedPokemon.getHp());
        }
        if (updatedPokemon.getSpeed() != 0) {
            existingPokemon.setSpeed(updatedPokemon.getSpeed());
        }
        if (updatedPokemon.getTypes() != null && !updatedPokemon.getTypes().isEmpty()) {
            existingPokemon.setTypes(updatedPokemon.getTypes());
        }
        if (updatedPokemon.getLocalisation() != null) {
            existingPokemon.setLocalisation(updatedPokemon.getLocalisation());
        }

        return pokemonRepository.save(existingPokemon);
    }

    public void deletePokemonById(UUID id) {
        pokemonRepository.deleteById(id);
    }

}
