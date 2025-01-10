package com.prapp.pt_crud_ecommerce.mapper;

import com.prapp.pt_crud_ecommerce.dao.entity.ProductEntity;
import com.prapp.pt_crud_ecommerce.expose.dto.ProductDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    List<ProductDto.Response> toResponseList(List<ProductEntity> entity);
    ProductEntity toRequest(ProductDto.Request request);
    ProductDto.Response toResponse(ProductEntity entity);
    ProductEntity toCreate (ProductDto.Request request);
}
