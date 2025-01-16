package com.example.api.core.product;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public interface ProductService {
  @GetMapping("/product/{productId}")
  ProductDto getProduct(@PathVariable Integer productId);

  @PostMapping("/product")
  ProductDto createProduct(@RequestBody ProductDto body);

  @DeleteMapping("/product/{productId}")
  void deleteProduct(@PathVariable Integer productId);
}
