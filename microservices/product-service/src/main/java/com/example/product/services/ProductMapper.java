package com.example.product.services;

import com.example.api.core.product.ProductDto;
import com.example.product.persistence.ProductEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProductMapper {

  @Mapping(source = "id", target = "productId")
  ProductDto entityToDto(ProductEntity entity);

  @Mapping(target = "id", ignore = true)
  @Mapping(target = "version", ignore = true)
  ProductEntity dtoToEntity(ProductDto dto);
}

