# Pokedex Weather App

Une application Spring Boot / Java 21 qui liste les Pokémon et ajuste leurs statistiques selon la météo actuelle.
La météo est récupérée via WeatherAPI, et les données sont mises en cache dans Redis pour de meilleures performances.
L’application est entièrement conteneurisée (Docker) et livrée avec un stack d’observabilité complet (Prometheus + Grafana).

## Fonctionnalités

- Liste tous les Pokémon depuis une base PostgreSQL.
- Désavantage les Pokémon de type Feu si la météo indique de la pluie.
- Utilise Redis pour le caching des données météo.
- Appel à l’API WeatherAPI pour obtenir la météo en temps réel.
- Expose des métriques techniques via Spring Boot Actuator et Micrometer.
- Monitoring complet avec Prometheus (scraping) et Grafana (dashboards).

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

4. Configurer la connexion à Grafana :
```
GRAFANA_ADMIN_USER=
GRAFANA_ADMIN_PASSWORD=
```

## Lancement de l'application

Build les images + création / run les containeurs :
```
docker compose -f docker-compose.dev.yml up --build -d
```

| Service            | URL                                                              | Description                        |
| ------------------ | ---------------------------------------------------------------- |------------------------------------|
| Pokédex API        | [http://localhost:8080](http://localhost:8080)                   | API principale Spring Boot         |
| Actuator / Metrics | [http://localhost:8080/actuator](http://localhost:8080/actuator) | Endpoints de supervision           |
| Prometheus         | [http://localhost:9090](http://localhost:9090)                   | Collecte et stockage des métriques |
| Grafana            | [http://localhost:3000](http://localhost:3000)                   | Dashboards de monitoring           |    
