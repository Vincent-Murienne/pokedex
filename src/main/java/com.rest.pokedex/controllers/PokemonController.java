package com.rest.pokedex.controllers;

import com.rest.pokedex.entities.Pokemon;
import com.rest.pokedex.services.PokemonService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/pokemons")
public class PokemonController {

    private final PokemonService pokemonService;

    public PokemonController(PokemonService pokemonService) {
        this.pokemonService = pokemonService;
    }

    @GetMapping
    public List<Pokemon> getAll() {
        return pokemonService.findAllPokemons();
    }

    @GetMapping("/weather/{location}")
    public List<Pokemon> getAllByWeather(@PathVariable String location) {
        return pokemonService.findAllPokemonsWithWeather(location);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pokemon> getById(@PathVariable UUID id) {
        return pokemonService.findPokemonById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Pokemon create(@RequestBody Pokemon pokemon) {
        return pokemonService.savePokemon(pokemon);
    }

    @PatchMapping("/{id}")
    public Pokemon update(@PathVariable UUID id, @RequestBody Pokemon updatedPokemon) {
        return pokemonService.updatePokemon(id, updatedPokemon);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable UUID id) {
        pokemonService.deletePokemonById(id);
    }
}
