package com.example.recommendation.persistence;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface RecommendationRepository extends
    PagingAndSortingRepository<RecommendationEntity, Integer>,
    CrudRepository<RecommendationEntity, Integer> {
  List<RecommendationEntity> findByProductId(Integer productId);

  void deleteByProductId(Integer productId);
}
