package com.example.recommendation.services;

import com.example.api.core.product.ProductDto;
import com.example.api.core.product.ProductService;
import com.example.api.core.recommendation.RecommendationDto;
import com.example.api.core.recommendation.RecommendationService;
import com.example.api.event.Event;
import java.util.function.Consumer;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class MessageProcessorConfig {
  private final RecommendationService recommendationService;

  @Bean
  public Consumer<Event<Integer, RecommendationDto>> messageProcessor() {
    return event -> {
      switch (event.getEventType()) {
        case CREATE:
          RecommendationDto recommendation = event.getData();
          recommendationService.createRecommendation(recommendation);
          break;
        case DELETE:
          int recommendationId = event.getKey();
          recommendationService.deleteRecommendations(recommendationId);
          break;
      }
    };
  }
}