package com.prapp.pt_crud_ecommerce.dao.repository;

import com.prapp.pt_crud_ecommerce.dao.entity.ProductEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductRepository extends MongoRepository<ProductEntity, String> {

}
