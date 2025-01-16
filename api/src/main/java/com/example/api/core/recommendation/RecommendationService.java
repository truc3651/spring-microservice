package com.example.api.core.recommendation;

import java.util.List;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

public interface RecommendationService {
  @PostMapping("/recommendation")
  void createRecommendation(@RequestBody RecommendationDto body);

  @GetMapping("/recommendation")
  List<RecommendationDto> getRecommendations(
      @RequestParam(value = "productId", required = true) int productId);

  @DeleteMapping(value = "/recommendation")
  void deleteRecommendations(@RequestParam(value = "productId", required = true)  int productId);
}
