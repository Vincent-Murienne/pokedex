package com.rest.pokedex.controllers;

import com.rest.pokedex.services.WeatherService;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/weather")
public class WeatherController {

    private final WeatherService weatherService;

    public WeatherController(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    /**
     * Retourne les villes où il pleut actuellement parmi une liste donnée
     * Exemple d'appel : /api/weather/rain?cities=Paris,Lyon,Bordeaux
     */
    @GetMapping("/rain")
    public List<String> getCitiesWithRain(@RequestParam List<String> cities) {
        List<String> rainingCities = new ArrayList<>();

        for (String city : cities) {
            String weather = weatherService.getWeather(city);
            if (weather.toLowerCase().contains("rain")) {
                rainingCities.add(city);
            }
        }

        return rainingCities;
    }
}
