package com.prapp.pt_crud_ecommerce.business.service;

import com.prapp.pt_crud_ecommerce.expose.dto.WeatherDTO;

public interface WeatherService {

    WeatherDTO getWeatherByCity(String city);
    WeatherDTO getWeatherByCoordinates(double lat, double lon);
}
