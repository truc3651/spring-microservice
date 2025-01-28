package com.example.composite.services.tracing;

import io.micrometer.observation.ObservationRegistry;
import org.springframework.boot.actuate.autoconfigure.observation.ObservationRegistryCustomizer;
import org.springframework.boot.info.BuildProperties;
import org.springframework.context.annotation.Configuration;

@Configuration(proxyBeanMethods = false)
public class ObservationRegistryConfig implements ObservationRegistryCustomizer<ObservationRegistry> {

  @Override
  public void customize(final ObservationRegistry registry) {
    registry.observationConfig().observationFilter(new BuildInfoObservationFilter());
  }
}
