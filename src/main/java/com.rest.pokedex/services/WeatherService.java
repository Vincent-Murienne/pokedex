package com.rest.pokedex.services;

import com.fasterxml.jackson.databind.JsonNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;

@Service
public class WeatherService {

    private final RestTemplate restTemplate;
    private final RedisTemplate<String, String> redisTemplate;
    private static final Logger log = LoggerFactory.getLogger(WeatherService.class);

    @Value("${weather.api.key}")
    private String apiKey;

    private static final String API_URL = "http://api.weatherapi.com/v1/current.json?key={apiKey}&q={location}";

    public WeatherService(RestTemplate restTemplate, RedisTemplate<String, String> redisTemplate) {
        this.restTemplate = restTemplate;
        this.redisTemplate = redisTemplate;
    }

    /**
     * Retourne la météo d'une localisation donnée (avec cache Redis)
     */
    public String getWeather(String location) {
        String cacheKey = "weather:" + location.toLowerCase();

        // Vérifie le cache Redis
        String cachedWeather = redisTemplate.opsForValue().get(cacheKey);
        if (cachedWeather != null) {
            log.info("Météo depuis Redis : " + cachedWeather);
            return cachedWeather;
        }

        // Appel API
        JsonNode response = restTemplate.getForObject(API_URL, JsonNode.class, apiKey, location);

        if (response == null || response.path("current").isMissingNode()) {
            log.warn("Impossible de récupérer la météo pour " + location);
        }

        String condition = response.path("current").path("condition").path("text").asText("Unknown");

        // Mise en cache
        redisTemplate.opsForValue().set(cacheKey, condition, Duration.ofMinutes(10));

        return condition;
    }
}
