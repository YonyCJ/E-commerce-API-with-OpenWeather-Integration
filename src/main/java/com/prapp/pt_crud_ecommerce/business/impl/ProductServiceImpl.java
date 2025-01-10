package com.prapp.pt_crud_ecommerce.business.impl;

import com.prapp.pt_crud_ecommerce.business.service.ProductService;
import com.prapp.pt_crud_ecommerce.dao.entity.ProductEntity;
import com.prapp.pt_crud_ecommerce.dao.repository.ProductRepository;
import com.prapp.pt_crud_ecommerce.excepcion.ModelNotFoundException;
import com.prapp.pt_crud_ecommerce.expose.dto.ProductDto;
import com.prapp.pt_crud_ecommerce.mapper.ProductMapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;


@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    @Override
    public List<ProductDto.Response> getAllProducts() {
        List<ProductEntity> entities = productRepository.findAll();
        return productMapper.toResponseList(entities);
    }

    @Override
    public ProductDto.Response getProductById(String id) {
        return productRepository.findById(id)
                .map(productMapper::toResponse)
                .orElseThrow(() -> new ModelNotFoundException("Product with ID " + id + " not found"));
    }

    @Override
    @Transactional
    public ProductDto.Response createProduct(ProductDto.Request request) {
        ProductEntity newProduct = productMapper.toCreate(request);
        newProduct.setCreatedAt(LocalDateTime.now());
        ProductEntity savedProduct = productRepository.save(newProduct);
        return productMapper.toResponse(savedProduct);
    }

    @Override
    @Transactional
    public ProductDto.Response updateProduct(String id, ProductDto.Request request) {
        return productRepository.findById(id)
                .map(existingProduct -> {
                    existingProduct.setName(request.getName());
                    existingProduct.setDescription(request.getDescription());
                    existingProduct.setPrice(request.getPrice());
                    existingProduct.setStock(request.getStock());
                    existingProduct.setUpdatedAt(LocalDateTime.now());
                    ProductEntity updatedProduct = productRepository.save(existingProduct);
                    return productMapper.toResponse(updatedProduct);
                })
                .orElseThrow(() -> new ModelNotFoundException("Product not found"));
    }

    @Override
    public void deleteProduct(String id) {
        if (!productRepository.existsById(id)) {
            throw new ModelNotFoundException("Product not found");
        }
        productRepository.deleteById(id);
    }
}
