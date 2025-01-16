package com.example.composite.services;

import com.example.api.composite.product.ProductAggregate;
import com.example.api.composite.product.ProductCompositeService;
import com.example.api.composite.product.RecommendationSummary;
import com.example.api.composite.product.ReviewSummary;
import com.example.api.core.product.ProductDto;
import com.example.api.core.recommendation.RecommendationDto;
import com.example.api.core.review.ReviewDto;
import com.example.composite.configs.ServiceDiscovery;
import com.example.composite.product.review.microservices.util.ServiceUtil;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductCompositeServiceImpl implements ProductCompositeService {
  private static final Logger LOG = LoggerFactory.getLogger(ProductCompositeServiceImpl.class);
  private final ProductCompositeIntegration integrationService;
  private final ServiceUtil serviceUtil;
  private final ServiceDiscovery serviceDiscovery;

  public ProductCompositeServiceImpl(
      ProductCompositeIntegration integrationService,
      ServiceUtil serviceUtil,
      ServiceDiscovery serviceDiscovery) {
    this.serviceUtil = serviceUtil;
    this.integrationService = integrationService;
    this.serviceDiscovery = serviceDiscovery;
  }

  @GetMapping("/your-endpoint")
  public String yourMethod() {
    return "response";
  }

  @Override
  public ProductAggregate getProduct(int productId) {
    ProductDto product = integrationService.getProduct(productId);
    List<RecommendationDto> recommendations = integrationService.getRecommendations(productId);
    List<ReviewDto> reviews = integrationService.getReviews(productId);

    List<RecommendationSummary> recommendationSummaries = recommendations
        .stream()
        .map(recommendationDto ->
            new RecommendationSummary(recommendationDto.getRecommendationId(),
                recommendationDto.getAuthor(),
                recommendationDto.getRating(),
                recommendationDto.getContent()))
        .toList();
    List<ReviewSummary> reviewSummaries = reviews
        .stream()
        .map(reviewDto ->
            new ReviewSummary(reviewDto.getReviewId(),
                reviewDto.getAuthor(),
                reviewDto.getSubject(),
                reviewDto.getContent()))
        .toList();

    return new ProductAggregate(
        product.getProductId(),
        product.getName(),
        product.getWeight(),
        recommendationSummaries,
        reviewSummaries
    );
  }

  @Override
  public void createProduct(ProductAggregate body) {
    ProductDto product = new ProductDto(body.getProductId(),
        body.getName(), body.getWeight());
    ProductDto savedProduct = integrationService.createProduct(product);
    Integer productId = savedProduct.getProductId();

    if (!CollectionUtils.isEmpty(body.getRecommendations())) {
      body.getRecommendations().forEach(r -> {
        RecommendationDto recommendation = new
            RecommendationDto(productId,
            r.getRecommendationId(), r.getAuthor(), r.getRate(),
            r.getContent());
        integrationService.createRecommendation(recommendation);
      });
    }

    if (!CollectionUtils.isEmpty(body.getReviews())) {
      body.getReviews().forEach(r -> {
        ReviewDto review = new ReviewDto(productId,
            r.getReviewId(), r.getAuthor(), r.getSubject(),
            r.getContent());
        integrationService.createReview(review);
      });
    }
  }

  @Override
  public void deleteProduct(int productId) {
    integrationService.deleteProduct(productId);
    integrationService.deleteRecommendations(productId);
    integrationService.deleteReviews(productId);
  }
}
