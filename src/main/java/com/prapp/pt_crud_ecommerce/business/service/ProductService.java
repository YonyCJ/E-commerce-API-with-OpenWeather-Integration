package com.prapp.pt_crud_ecommerce.business.service;

import com.prapp.pt_crud_ecommerce.expose.dto.ProductDto;

import java.util.List;

public interface ProductService {
    List<ProductDto.Response> getAllProducts();
    ProductDto.Response getProductById(String id);
    ProductDto.Response createProduct(ProductDto.Request request);
    ProductDto.Response updateProduct(String id, ProductDto.Request request);
    void deleteProduct(String id);
}
