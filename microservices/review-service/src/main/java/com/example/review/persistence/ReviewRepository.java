package com.example.review.persistence;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ReviewRepository extends
    PagingAndSortingRepository<ReviewEntity, Integer>,
    CrudRepository<ReviewEntity, Integer> {
  List<ReviewEntity> findByProductId(int productId);

  void deleteByProductId(int productId);
}
