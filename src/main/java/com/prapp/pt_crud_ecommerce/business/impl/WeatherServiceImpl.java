package com.prapp.pt_crud_ecommerce.business.impl;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.prapp.pt_crud_ecommerce.business.service.WeatherService;
import com.prapp.pt_crud_ecommerce.expose.dto.WeatherDTO;
import io.micrometer.common.util.StringUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
@RequiredArgsConstructor
public class WeatherServiceImpl implements WeatherService {

    @Value("${openweather.api.key}")
    private String apiKey;

    @Value("${openweather.api.url}")
    private String apiUrl;

    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;

    @Override
    public WeatherDTO getWeatherByCity(String city) {
        if (StringUtils.isBlank(city)) {
            throw new IllegalArgumentException("City cannot be empty");
        }
        String url = buildUrlForCity(city);
        return getWeatherData(url);
    }

    @Override
    public WeatherDTO getWeatherByCoordinates(double lat, double lon) {
        validateCoordinates(lat, lon);
        String url = buildUrlForCoordinates(lat, lon);
        return getWeatherData(url);
    }


    private String buildUrlForCity(String city) {
        return UriComponentsBuilder.fromHttpUrl(apiUrl)
                .queryParam("q", city)
                .queryParam("appid", apiKey)
                .queryParam("units", "metric")
                .build()
                .toUriString();
    }

    private String buildUrlForCoordinates(Double lat, Double lon) {
        return UriComponentsBuilder.fromHttpUrl(apiUrl)
                .queryParam("lat", lat)
                .queryParam("lon", lon)
                .queryParam("appid", apiKey)
                .queryParam("units", "metric")
                .build()
                .toUriString();
    }

    private WeatherDTO getWeatherData(String url) {
        try {
            String response = restTemplate.getForObject(url, String.class);
            JsonNode root = objectMapper.readTree(response);

            return mapToWeatherDTO(root);
        } catch (Exception e) {
            throw new RuntimeException("Error fetching weather data", e);
        }
    }

    private WeatherDTO mapToWeatherDTO(JsonNode root) {
        WeatherDTO weatherDTO = new WeatherDTO();
        weatherDTO.setLocation(root.path("name").asText());
        weatherDTO.setTemperature(root.path("main").path("temp").asDouble());
        weatherDTO.setHumidity(root.path("main").path("humidity").asDouble());
        weatherDTO.setDescription(root.path("weather").get(0).path("description").asText());
        return weatherDTO;
    }

    private void validateCoordinates(Double lat, Double lon) {
        if (lat == null || lon == null) {
            throw new IllegalArgumentException("Coordinates cannot be null");
        }
        if (lat < -90 || lat > 90) {
            throw new IllegalArgumentException("Invalid latitude value");
        }
        if (lon < -180 || lon > 180) {
            throw new IllegalArgumentException("Invalid longitude value");
        }
    }
}
