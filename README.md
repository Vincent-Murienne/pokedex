# Pokedex Weather App

Une application Spring Boot qui liste les Pokémon et ajuste leurs statistiques en fonction de la météo actuelle via l'API WeatherAPI. Les résultats sont mis en cache dans Redis pour optimiser les performances.

## Fonctionnalités

- Liste tous les Pokémon depuis une base PostgreSQL.
- Désavantage les Pokémon de type Feu si la météo indique de la pluie.
- Utilise Redis pour le caching des données météo.
- Appel à l’API WeatherAPI pour obtenir la météo en temps réel.

## Technologies

- Java 21 / Spring Boot
- PostgreSQL
- Redis
- Docker / Docker Compose
- Maven

## Prérequis

- Docker & Docker Compose installés
- Clé API WeatherAPI

## Configuration

1. Renommer le fichier `.env.example` en `.env` :  

2. Copier la clé API dans le fichier `.env` :  
```
WEATHER_API_KEY=
```
3. Configurer la connexion PostgreSQL et Spring :
```
POSTGRES_DB=
POSTGRES_USER=
POSTGRES_PASSWORD=

SPRING_DATASOURCE_URL=
SPRING_DATASOURCE_USERNAME=
SPRING_DATASOURCE_PASSWORD=
```

## Lancement de l'application

Build les images + création / run les containeurs :
```
 docker-compose up --build -d 
 ```
