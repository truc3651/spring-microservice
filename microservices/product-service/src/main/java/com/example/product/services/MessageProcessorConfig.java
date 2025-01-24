package com.example.product.services;

import com.example.api.core.product.ProductDto;
import com.example.api.core.product.ProductService;
import com.example.api.event.Event;
import com.example.api.exceptions.EventProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
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
      Integer productId = event.getKey();
      LOG.info("Process event product with id {}", productId);

      switch (event.getEventType()) {
        case CREATE:
          LOG.info("Create product with ID: {}", productId);
          ProductDto product = event.getData();
          productService.createProduct(product);
          break;

        case DELETE:
          LOG.info("Delete product with ProductID: {}", productId);
          productService.deleteProduct(productId);
          break;
      }
      LOG.info("Message processing done!");
    };
  }
}
