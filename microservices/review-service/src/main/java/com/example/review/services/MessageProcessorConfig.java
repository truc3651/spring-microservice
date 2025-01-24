package com.example.review.services;

import com.example.api.core.review.ReviewDto;
import com.example.api.core.review.ReviewService;
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
  private final ReviewService reviewService;

  @Bean
  public Consumer<Event<Integer, ReviewDto>> messageProcessor() {
    return event -> {
      Integer productId = event.getKey();
      LOG.info("Process event review with product id {}", productId);

      switch (event.getEventType()) {
        case CREATE:
          LOG.info("Create review with product id: {}", productId);
          ReviewDto review = event.getData();
          reviewService.createReview(review);
          break;
        case DELETE:
          LOG.info("Delete review with product id: {}", productId);
          reviewService.deleteReview(productId);
          break;
      }
      LOG.info("Message processing done!");
    };
  }
}
