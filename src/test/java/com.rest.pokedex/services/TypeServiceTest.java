package com.rest.pokedex.services;

import com.rest.pokedex.entities.Type;
import com.rest.pokedex.repository.TypeRepository;
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

class TypeServiceTest {

    @Mock
    private TypeRepository typeRepository;

    @InjectMocks
    private TypeService typeService;

    private Type type;
    private UUID typeId;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        typeId = UUID.randomUUID();
        type = new Type();
        type.setId(typeId);
        type.setNom("Electric");
    }

    @Test
    void testFindAllTypes() {
        List<Type> types = Arrays.asList(type);
        when(typeRepository.findAll()).thenReturn(types);

        List<Type> result = typeService.findAllTypes();

        assertEquals(1, result.size());
        assertEquals("Electric", result.get(0).getNom());
        verify(typeRepository, times(1)).findAll();
    }

    @Test
    void testFindTypeById() {
        when(typeRepository.findById(typeId)).thenReturn(Optional.of(type));

        Optional<Type> result = typeService.findTypeById(typeId);

        assertTrue(result.isPresent());
        assertEquals("Electric", result.get().getNom());
        verify(typeRepository, times(1)).findById(typeId);
    }

    @Test
    void testSaveType() {
        when(typeRepository.save(type)).thenReturn(type);

        Type result = typeService.saveType(type);

        assertNotNull(result);
        assertEquals("Electric", result.getNom());
        verify(typeRepository, times(1)).save(type);
    }

    @Test
    void testDeleteTypeById() {
        doNothing().when(typeRepository).deleteById(typeId);

        typeService.deleteTypeById(typeId);

        verify(typeRepository, times(1)).deleteById(typeId);
    }
}
