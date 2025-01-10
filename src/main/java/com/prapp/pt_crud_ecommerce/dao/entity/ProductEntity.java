package com.prapp.pt_crud_ecommerce.dao.entity;

import jakarta.persistence.Id;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Document(collection = "ecommercedb")
public class ProductEntity implements Serializable {

        @Id
        private String id;
        private String name;
        private String description;
        private BigDecimal price;
        private Integer stock;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;
}
