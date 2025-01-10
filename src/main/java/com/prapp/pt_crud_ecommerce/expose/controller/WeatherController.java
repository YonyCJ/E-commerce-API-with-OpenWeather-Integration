package com.prapp.pt_crud_ecommerce.expose.controller;

import com.prapp.pt_crud_ecommerce.business.service.WeatherService;
import com.prapp.pt_crud_ecommerce.expose.dto.WeatherDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/weather")
@RequiredArgsConstructor
public class WeatherController {
    private final WeatherService weatherService;

    @GetMapping
    public ResponseEntity<WeatherDTO> getWeather(
            @RequestParam(required = false) String city,
            @RequestParam(required = false) Double lat,
            @RequestParam(required = false) Double lon) {

        if ((city == null || city.trim().isEmpty()) && (lat == null || lon == null || lat == 0.0 || lon == 0.0)) {
            WeatherDTO emptyWeatherDTO = new WeatherDTO();
            emptyWeatherDTO.setLocation("Please provide either a city or coordinates (lat, lon).");
            return ResponseEntity.badRequest().body(emptyWeatherDTO);
        }
        WeatherDTO weather;
        if (city != null) {
            weather = weatherService.getWeatherByCity(city);
        } else if (lat != null && lon != null) {
            weather = weatherService.getWeatherByCoordinates(lat, lon);
        } else {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(weather);
    }
}
