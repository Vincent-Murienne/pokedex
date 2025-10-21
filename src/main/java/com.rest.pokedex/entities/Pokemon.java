package com.rest.pokedex.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "pokemon")
public class Pokemon {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private UUID id;

    @Column(name = "numero_pokemon", unique = true, nullable = false)
    private int numeroPokemon;

    @Column(name = "nom", nullable = false)
    private String nom;

    @Column(name = "hp")
    private int hp;

    @Column(name = "attack")
    private int attack;

    @Column(name = "defense")
    private int defense;

    @Column(name = "sp_atk")
    private int spAtk;

    @Column(name = "sp_def")
    private int spDef;

    @Column(name = "speed")
    private int speed;

    @ManyToOne
    @JoinColumn(name = "id_localisation")
    private Localisation localisation;

    @ManyToMany
    @JoinTable(
            name = "pokemon_type",
            joinColumns = @JoinColumn(name = "id_pokemon"),
            inverseJoinColumns = @JoinColumn(name = "id_type")
    )
    private Set<Type> types = new HashSet<>();
}
