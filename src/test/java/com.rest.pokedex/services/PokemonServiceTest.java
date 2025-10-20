package com.rest.pokedex.services;

import com.rest.pokedex.entities.Pokemon;
import com.rest.pokedex.repository.PokemonRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PokemonServiceTest {

    @Mock
    private PokemonRepository pokemonRepository;

    @InjectMocks
    private PokemonService pokemonService;

    private Pokemon pokemon;
    private UUID pokemonId;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        pokemonId = UUID.randomUUID();
        pokemon = new Pokemon();
        pokemon.setId(pokemonId);
        pokemon.setNom("Pikachu");
    }

    @Test
    void testFindAllPokemons() {
        List<Pokemon> pokemons = Arrays.asList(pokemon);
        when(pokemonRepository.findAll()).thenReturn(pokemons);

        List<Pokemon> result = pokemonService.findAllPokemons();

        assertEquals(1, result.size());
        assertEquals("Pikachu", result.get(0).getNom());
        verify(pokemonRepository, times(1)).findAll();
    }

    @Test
    void testFindPokemonById() {
        when(pokemonRepository.findById(pokemonId)).thenReturn(Optional.of(pokemon));

        Optional<Pokemon> result = pokemonService.findPokemonById(pokemonId);

        assertTrue(result.isPresent());
        assertEquals("Pikachu", result.get().getNom());
        verify(pokemonRepository, times(1)).findById(pokemonId);
    }

    @Test
    void testSavePokemon() {
        when(pokemonRepository.save(pokemon)).thenReturn(pokemon);

        Pokemon result = pokemonService.savePokemon(pokemon);

        assertNotNull(result);
        assertEquals("Pikachu", result.getNom());
        verify(pokemonRepository, times(1)).save(pokemon);
    }

    @Test
    void testDeletePokemonById() {
        doNothing().when(pokemonRepository).deleteById(pokemonId);

        pokemonService.deletePokemonById(pokemonId);

        verify(pokemonRepository, times(1)).deleteById(pokemonId);
    }
}
