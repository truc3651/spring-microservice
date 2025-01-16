package com.example.review.services;

import com.example.api.core.review.ReviewDto;
import com.example.api.core.review.ReviewService;
import com.example.api.exceptions.InvalidInputException;
import com.example.review.persistence.ReviewEntity;
import com.example.review.persistence.ReviewRepository;
import java.util.List;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class ReviewServiceImpl implements ReviewService {
  private static final Logger LOG = LoggerFactory.getLogger(ReviewServiceImpl.class);
  private final ReviewRepository repository;
  private final ReviewMapper mapper;

  @Override
  public List<ReviewDto> getReviews(int productId) {
    if (productId < 1) {
      throw new InvalidInputException("Invalid productId: " + productId);
    }

    List<ReviewEntity> reviewEntities = repository.findByProductId(productId);
    List<ReviewDto> reviewDtos = mapper.listEntitiesToListDto(reviewEntities);
    LOG.debug("reviews response size: {}", reviewDtos.size());

    return reviewDtos;
  }

  @Override
  public void createReview(ReviewDto dto) {
    ReviewEntity entity = mapper.dtoToEntity(dto);
    repository.save(entity);
  }

  @Override
  public void deleteReview(Integer productId) {
    repository.deleteByProductId(productId);
  }
}
