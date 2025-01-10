package com.prapp.pt_crud_ecommerce.expose.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

public class ProductDto {

    @Getter
    @Setter
    public static class Response {
        private String id;
        private String name;
        private String description;
        private BigDecimal price;
        private Integer stock;
    }

    @Getter
    @Setter
    public static class Request {
        private String id;
        @NotBlank(message = "Name cannot be blank")
        @NotNull(message = "Name cannot be null")
        private String name;
        @NotBlank(message = "Name cannot be blank")
        @NotNull(message = "Description cannot be null")
        private String description;
        @NotNull(message = "Price cannot be null")
        private BigDecimal price;
        @NotNull(message = "Stock cannot be null")
        private Integer stock;
    }

}
