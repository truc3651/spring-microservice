package com.example.review.services;

import com.example.api.core.recommendation.RecommendationDto;
import com.example.api.core.recommendation.RecommendationService;
import com.example.api.core.review.ReviewDto;
import com.example.api.core.review.ReviewService;
import com.example.api.event.Event;
import java.util.function.Consumer;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class MessageProcessorConfig {
  private final ReviewService reviewService;

  @Bean
  public Consumer<Event<Integer, ReviewDto>> messageProcessor() {
    return event -> {
      switch (event.getEventType()) {
        case CREATE:
          ReviewDto review = event.getData();
          reviewService.createReview(review);
          break;
        case DELETE:
          int reviewId = event.getKey();
          reviewService.deleteReview(reviewId);
          break;
      }
    };
  }
}
