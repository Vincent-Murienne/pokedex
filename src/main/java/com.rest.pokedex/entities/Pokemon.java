package com.rest.pokedex.entities;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
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

    @Column(name = "hp") // Colonne hp
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

    public Pokemon() {}

    public Pokemon(UUID id, int numeroPokemon, String nom, int hp, int attack, int defense, int spAtk, int spDef, int speed, Localisation localisation, Set<Type> types) {
        this.id = id;
        this.numeroPokemon = numeroPokemon;
        this.nom = nom;
        this.hp = hp;
        this.attack = attack;
        this.defense = defense;
        this.spAtk = spAtk;
        this.spDef = spDef;
        this.speed = speed;
        this.localisation = localisation;
        this.types = types;
    }

    public UUID getId() { return id; }
    public int getNumeroPokemon() { return numeroPokemon; }
    public String getNom() { return nom; }
    public int getHp() { return hp; }
    public int getAttack() { return attack; }
    public int getDefense() { return defense; }
    public int getSpAtk() { return spAtk; }
    public int getSpDef() { return spDef; }
    public int getSpeed() { return speed; }
    public Localisation getLocalisation() { return localisation; }
    public Set<Type> getTypes() { return types; }

    public void setId(UUID id) { this.id = id; }
    public void setNumeroPokemon(int numeroPokemon) { this.numeroPokemon = numeroPokemon; }
    public void setNom(String nom) { this.nom = nom; }
    public void setHp(int hp) { this.hp = hp; }
    public void setAttack(int attack) { this.attack = attack; }
    public void setDefense(int defense) { this.defense = defense; }
    public void setSpAtk(int spAtk) { this.spAtk = spAtk; }
    public void setSpDef(int spDef) { this.spDef = spDef; }
    public void setSpeed(int speed) { this.speed = speed; }
    public void setLocalisation(Localisation localisation) { this.localisation = localisation; }
    public void setTypes(Set<Type> types) { this.types = types; }

    @Override
    public String toString() {
        return "Pokemon{" +
                "id=" + id +
                ", numeroPokemon=" + numeroPokemon +
                ", nom='" + nom + '\'' +
                ", hp=" + hp +
                ", attack=" + attack +
                ", defense=" + defense +
                ", spAtk=" + spAtk +
                ", spDef=" + spDef +
                ", speed=" + speed +
                ", idLocalisation=" + localisation +
                ", types=" + types +
                '}';
    }
}
