package com.example.product.services;

import com.example.api.core.product.ProductDto;
import com.example.api.core.product.ProductService;
import com.example.api.exceptions.InvalidInputException;
import com.example.api.exceptions.NotFoundException;
import com.example.composite.product.review.microservices.util.ServiceUtil;
import com.example.product.persistence.ProductEntity;
import com.example.product.persistence.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

  private static final Logger LOG = LoggerFactory.getLogger(ProductServiceImpl.class);

  private final ServiceUtil serviceUtil;
  private final ProductMapper mapper;
  private final ProductRepository repository;

  @Override
  public ProductDto getProduct(Integer productId) {
    if (productId < 1) {
      throw new InvalidInputException("Invalid productId: " + productId);
    }
    ProductEntity entity = repository
        .findById(productId)
        .orElseThrow(() -> new NotFoundException("No product found for productId: " + productId));
    return mapper.entityToDto(entity);
  }

  @Override
  public ProductDto createProduct(ProductDto request) {
    ProductEntity entity = repository.save(mapper.dtoToEntity(request));
    return mapper.entityToDto(entity);
  }

  @Override
  public void deleteProduct(Integer productId) {
    repository.deleteById(productId);
  }
}
