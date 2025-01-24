package com.example.gateway;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties("app")
public class ServiceDiscovery {
  private String productComposite;
  private String productService;
  private String recommendationService;
  private String reviewService;

  public String getProductComposite() {
    return productComposite;
  }

  public String getProductService() {
    return productService;
  }

  public String getRecommendationService() {
    return recommendationService;
  }

  public String getReviewService() {
    return reviewService;
  }

  public void setProductComposite(String productComposite) {
    this.productComposite = productComposite;
  }

  public void setProductService(String productService) {
    this.productService = productService;
  }

  public void setRecommendationService(String recommendationService) {
    this.recommendationService = recommendationService;
  }

  public void setReviewService(String reviewService) {
    this.reviewService = reviewService;
  }
}
