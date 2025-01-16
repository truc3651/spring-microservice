package com.example.product.persistence;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ProductRepository extends
    PagingAndSortingRepository<ProductEntity, Integer>,
    CrudRepository<ProductEntity, Integer> {

}
