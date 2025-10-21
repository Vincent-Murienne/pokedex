package com.rest.pokedex.services;

import com.rest.pokedex.entities.Type;
import com.rest.pokedex.repository.TypeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class TypeService {

    private final TypeRepository typeRepository;

    public TypeService(TypeRepository typeRepository) {
        this.typeRepository = typeRepository;
    }

    public List<Type> findAllTypes() {
        return typeRepository.findAll();
    }

    public Optional<Type> findTypeById(UUID id) {
        return typeRepository.findById(id);
    }

    public Type saveType(Type type) {
        return typeRepository.save(type);
    }

    public Type updateType(UUID id, Type updatedType) {
        Type existingType = typeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Type non trouvé avec l'id: " + id));

        existingType.setNom(updatedType.getNom());

        return typeRepository.save(existingType);
    }

    public void deleteTypeById(UUID id) {
        typeRepository.deleteById(id);
    }
}
