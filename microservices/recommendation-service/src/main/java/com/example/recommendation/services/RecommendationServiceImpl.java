package com.example.recommendation.services;

import com.example.api.core.recommendation.RecommendationDto;
import com.example.api.core.recommendation.RecommendationService;
import com.example.api.exceptions.InvalidInputException;
import com.example.recommendation.persistence.RecommendationEntity;
import com.example.recommendation.persistence.RecommendationRepository;
import java.util.List;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class RecommendationServiceImpl implements RecommendationService {
  private static final Logger LOG = LoggerFactory.getLogger(RecommendationServiceImpl.class);
  private final RecommendationMapper mapper;
  private final RecommendationRepository repository;

  @Override
  public List<RecommendationDto> getRecommendations(int productId) {
    if (productId < 1) {
      throw new InvalidInputException("Invalid productId: " + productId);
    }

    List<RecommendationEntity> recommendationEntities = repository.findByProductId(productId);
    List<RecommendationDto> recommendationDtos = mapper.listEntitiesToListDto(recommendationEntities);

    LOG.debug("recommendations response size: {}", recommendationDtos.size());

    return recommendationDtos;
  }

  @Override
  public void createRecommendation(RecommendationDto dto) {
    RecommendationEntity entity = mapper.dtoToEntity(dto);
    repository.save(entity);
  }

  @Override
  public void deleteRecommendations(int productId) {
    repository.deleteByProductId(productId);
  }
}
