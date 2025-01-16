package com.example.recommendation.services;

import com.example.api.core.recommendation.RecommendationDto;
import com.example.recommendation.persistence.RecommendationEntity;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RecommendationMapper {
  @Mapping(source = "id", target = "recommendationId")
  RecommendationDto entityToDto(RecommendationEntity entity);

  default List<RecommendationDto> listEntitiesToListDto(List<RecommendationEntity> entities) {
    return entities
        .stream()
        .map(this::entityToDto)
        .toList();
  }

  @Mapping(target = "id", ignore = true)
  @Mapping(target = "version", ignore = true)
  RecommendationEntity dtoToEntity(RecommendationDto dto);
}
