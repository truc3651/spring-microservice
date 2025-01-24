package com.example.recommendation.services;

import com.example.api.core.recommendation.RecommendationDto;
import com.example.api.core.recommendation.RecommendationService;
import com.example.api.event.Event;
import java.util.function.Consumer;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class MessageProcessorConfig {
  private static final Logger LOG = LoggerFactory.getLogger(MessageProcessorConfig.class);
  private final RecommendationService recommendationService;

  @Bean
  public Consumer<Event<Integer, RecommendationDto>> messageProcessor() {
    return event -> {
      Integer productId = event.getKey();
      LOG.info("Process event recommendation with product id {}", productId);

      switch (event.getEventType()) {
        case CREATE:
          LOG.info("Create recommendation with product id: {}", productId);
          RecommendationDto recommendation = event.getData();
          recommendationService.createRecommendation(recommendation);
          break;
        case DELETE:
          LOG.info("Delete recommendation with product id: {}", productId);
          recommendationService.deleteRecommendations(productId);
          break;
      }
      LOG.info("Message processing done!");
    };
  }
}