package com.example.gateway;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.health.CompositeHealthContributor;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthContributor;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.boot.actuate.health.NamedContributor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

//@Component
//public class HealthCheckConfiguration implements CompositeHealthContributor {
//  private static final Logger LOG = LoggerFactory.getLogger(HealthCheckConfiguration.class);
//  private Map<String, HealthContributor> contributors = new LinkedHashMap<>();
//  private final RestTemplate restTemplate;
//
//  @Autowired
//  public HealthCheckConfiguration(ServiceDiscovery serviceDiscovery, RestTemplate restTemplate) {
//    this.restTemplate = restTemplate;
//    contributors.put("product", (HealthIndicator) () -> getHealth(serviceDiscovery.getProductService()));
//    contributors.put("recommendation", (HealthIndicator) () -> getHealth(serviceDiscovery.getRecommendationService()));
//    contributors.put("review", (HealthIndicator) () -> getHealth(serviceDiscovery.getReviewService()));
//    contributors.put("product-composite", (HealthIndicator) () -> getHealth(serviceDiscovery.getProductComposite()));
//  }
//
//  @Override
//  public HealthContributor getContributor(String name) {
//    return contributors.get(name);
//  }
//
//  @Override
//  public Iterator<NamedContributor<HealthContributor>> iterator() {
//    return contributors.entrySet().stream()
//        .map((entry) ->
//            NamedContributor.of(entry.getKey(),
//                entry.getValue())).iterator();
//  }
//
//  private Health getHealth(String url) {
//    url += "/actuator/health";
//    try {
//      restTemplate.getForEntity(url, String.class);
//      return new Health.Builder()
//          .up()
//          .build();
//    } catch (Exception ex) {
//      LOG.warn("Health check failed for {}", url, ex);
//      return new Health.Builder()
//          .down(ex)
//          .build();
//    }
//  }
//}



public class HealthCheckConfiguration {}