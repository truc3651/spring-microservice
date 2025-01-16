package com.example.product.services;

import com.example.api.core.product.ProductDto;
import com.example.api.core.product.ProductService;
import com.example.api.event.Event;
import com.example.api.exceptions.EventProcessingException;
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
  private final ProductService productService;

  @Bean
  public Consumer<Event<Integer, ProductDto>> messageProcessor() {
    return event -> {
      LOG.info("Process message created at {}...", event.getEventCreatedAt());

      switch (event.getEventType()) {

        case CREATE:
          ProductDto product = event.getData();
          LOG.info("Create product with ID: {}", product.getProductId());
          productService.createProduct(product);
          break;

        case DELETE:
          int productId = event.getKey();
          LOG.info("Delete product with ProductID: {}", productId);
          productService.deleteProduct(productId);
          break;

        default:
          String errorMessage = "Incorrect event type: " + event.getEventType() + ", expected a CREATE or DELETE event";
          LOG.warn(errorMessage);
          throw new EventProcessingException(errorMessage);
      }

      LOG.info("Message processing done!");

    };
  }
}
