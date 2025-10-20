package com.rest.pokedex.controllers;

import com.rest.pokedex.entities.Type;
import com.rest.pokedex.services.TypeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/types")
public class TypeController {

    private final TypeService typeService;

    public TypeController(TypeService typeService) {
        this.typeService = typeService;
    }

    @GetMapping
    public List<Type> getAll() {
        return typeService.findAllTypes();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Type> getById(@PathVariable UUID id) {
        return typeService.findTypeById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Type create(@RequestBody Type type) {
        return typeService.saveType(type);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable UUID id) {
        typeService.deleteTypeById(id);
    }
}
