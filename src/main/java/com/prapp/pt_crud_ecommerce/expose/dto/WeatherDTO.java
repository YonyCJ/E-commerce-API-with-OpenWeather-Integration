package com.prapp.pt_crud_ecommerce.expose.dto;

import lombok.Data;

@Data
public class WeatherDTO {
    private String location;
    private Double temperature;
    private Double humidity;
    private String description;
}
