package com.example.review.services;

import com.example.api.core.review.ReviewDto;
import com.example.review.persistence.ReviewEntity;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ReviewMapper {
  @Mapping(source = "id", target = "reviewId")
  ReviewDto entityToDto(ReviewEntity entity);

  default List<ReviewDto> listEntitiesToListDto(List<ReviewEntity> entities) {
    return entities
        .stream()
        .map(this::entityToDto)
        .toList();
  }

  @Mapping(target = "id", ignore = true)
  @Mapping(target = "version", ignore = true)
  ReviewEntity dtoToEntity(ReviewDto dto);
}
