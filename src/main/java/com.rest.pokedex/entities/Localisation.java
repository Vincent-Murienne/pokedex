package com.rest.pokedex.entities;

import jakarta.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "localisation")
public class Localisation {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private UUID id;

    @Column(name = "nom", nullable = false)
    private String nom;

    public Localisation() {}

    public Localisation(UUID id, String nom) {
        this.id = id;
        this.nom = nom;
    }

    public UUID getId() { return id; }
    public String getNom() { return nom; }

    public void setId(UUID id) { this.id = id; }
    public void setNom(String nom) { this.nom = nom; }
}
