package com.example.api.core.recommendation;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RecommendationDto {
  private Integer productId;
  private Integer recommendationId;
  private String author;
  private int rating;
  private String content;
}
