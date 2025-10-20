package com.rest.pokedex.services;

import com.rest.pokedex.entities.Localisation;
import com.rest.pokedex.repository.LocalisationRepository;
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

class LocalisationServiceTest {

    @Mock
    private LocalisationRepository localisationRepository;

    @InjectMocks
    private LocalisationService localisationService;

    private Localisation localisation;
    private UUID localisationId;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        localisationId = UUID.randomUUID();
        localisation = new Localisation();
        localisation.setId(localisationId);
        localisation.setNom("Paris");
    }

    @Test
    void testFindAllLocalisations() {
        List<Localisation> localisations = Arrays.asList(localisation);
        when(localisationRepository.findAll()).thenReturn(localisations);

        List<Localisation> result = localisationService.findAllLocalisations();

        assertEquals(1, result.size());
        assertEquals("Paris", result.get(0).getNom());
        verify(localisationRepository, times(1)).findAll();
    }

    @Test
    void testFindLocalisationById() {
        when(localisationRepository.findById(localisationId)).thenReturn(Optional.of(localisation));

        Optional<Localisation> result = localisationService.findLocalisationById(localisationId);

        assertTrue(result.isPresent());
        assertEquals("Paris", result.get().getNom());
        verify(localisationRepository, times(1)).findById(localisationId);
    }

    @Test
    void testSaveLocalisation() {
        when(localisationRepository.save(localisation)).thenReturn(localisation);

        Localisation result = localisationService.saveLocalisation(localisation);

        assertNotNull(result);
        assertEquals("Paris", result.getNom());
        verify(localisationRepository, times(1)).save(localisation);
    }

    @Test
    void testDeleteLocalisationById() {
        doNothing().when(localisationRepository).deleteById(localisationId);

        localisationService.deleteLocalisationById(localisationId);

        verify(localisationRepository, times(1)).deleteById(localisationId);
    }
}
