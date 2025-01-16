package com.example.composite.services;

import static com.example.api.event.Event.Type.CREATE;

import com.example.api.core.product.ProductDto;
import com.example.api.core.recommendation.RecommendationDto;
import com.example.api.core.review.ReviewDto;
import com.example.api.event.Event;
import com.example.api.exceptions.InvalidInputException;
import com.example.api.exceptions.NotFoundException;
import com.example.composite.configs.ServiceDiscovery;
import com.example.composite.product.review.microservices.util.ErrorMessage;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Component
@RequiredArgsConstructor
public class ProductCompositeIntegration {
  private final Logger LOG = LoggerFactory.getLogger(ProductCompositeIntegration.class);
  private final ServiceDiscovery serviceDiscovery;
  private final RestTemplate restTemplate;
  private final ObjectMapper objectMapper;
  private final StreamBridge streamBridge;

  public List<RecommendationDto> getRecommendations(int productId) {
    try {
      String url = serviceDiscovery.getRecommendationService() + "/recommendation?productId=" + productId;
      LOG.debug("About to call recommendation service {}", url);
      List<RecommendationDto> result = restTemplate.exchange(
          url,
          HttpMethod.GET,
          null,
          new ParameterizedTypeReference<List<RecommendationDto>>() {})
          .getBody();

      LOG.debug("Found recommendation with size {} and productId {}", result.size(), productId);
      return result;

    } catch (Exception e) {
      LOG.debug("Something happened when trying to list recommendations: {}", e.getMessage());
      return List.of();
    }
  }

  public List<ReviewDto> getReviews(int productId) {
    try {
      String url = serviceDiscovery.getReviewService() + "/review?productId=" + productId;
      LOG.debug("About to call review service {}", url);
      List<ReviewDto> result = restTemplate.exchange(
              url,
              HttpMethod.GET,
              null,
              new ParameterizedTypeReference<List<ReviewDto>>() {})
          .getBody();

      LOG.debug("Found review with size {} and productId {}", result.size(), productId);
      return result;

    } catch (Exception e) {
      LOG.debug("Something happened when trying to list reviews: {}", e.getMessage());
      return List.of();
    }
  }

  public ProductDto getProduct(int productId) {
    try {
      String url = serviceDiscovery.getProductService() + "/product/" + productId;
      LOG.debug("About to call product service {}", url);
      ProductDto result = restTemplate.getForObject(
              url,
              ProductDto.class);

      LOG.debug("Found product with productId {}", productId);
      return result;

    } catch (HttpClientErrorException ex) {
      switch (HttpStatus.resolve(ex.getStatusCode().value())) {
        case NOT_FOUND:
          throw new NotFoundException(getErrorMessage(ex));

        case UNPROCESSABLE_ENTITY:
          throw new InvalidInputException(getErrorMessage(ex));

        default:
          LOG.warn("Got an unexpected HTTP error: {}, will rethrow it", ex.getStatusCode());
          LOG.warn("Error body: {}", ex.getResponseBodyAsString());
          throw ex;
      }
    }
  }

  public ProductDto createProduct(ProductDto body) {
    sendMessage("products-out-0",
        new Event(CREATE, body.getProductId(), body));
    return body;
  }

  public void deleteProduct(int productId) {
    String url = serviceDiscovery.getProductService() + "/product/" + productId;
    restTemplate.delete(url);
  }

  public void createRecommendation(RecommendationDto body) {
    String url = serviceDiscovery.getRecommendationService() + "/recommendation";
    restTemplate.postForObject(
        url, body, RecommendationDto.class);
  }

  public void deleteRecommendations(int productId) {
    String url = serviceDiscovery.getRecommendationService() + "/recommendation/" + productId;
    restTemplate.delete(url);
  }

  public void createReview(ReviewDto body) {
    String url = serviceDiscovery.getReviewService() + "/review";
    restTemplate.postForObject(
        url, body, RecommendationDto.class);
  }

  public void deleteReviews(int productId) {
    String url = serviceDiscovery.getReviewService() + "/review/" + productId;
    restTemplate.delete(url);
  }

  private String getErrorMessage(HttpClientErrorException ex) {
    try {
      return objectMapper.readValue(ex.getResponseBodyAsString(), ErrorMessage.class).getMessage();
    } catch (IOException ioex) {
      return ex.getMessage();
    }
  }

  private void sendMessage(String bindingName, Event event) {
    System.out.println(">>event " + event);
    Message<?> message = MessageBuilder.withPayload(event)
        .setHeader("partitionKey", event.getKey())
        .build();
    streamBridge.send(bindingName, message);
  }
}