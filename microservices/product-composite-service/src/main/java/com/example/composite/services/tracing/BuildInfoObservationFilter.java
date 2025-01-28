package com.example.composite.services.tracing;

import io.micrometer.common.KeyValue;
import io.micrometer.observation.Observation;
import io.micrometer.observation.ObservationFilter;
import org.springframework.boot.info.BuildProperties;

public class BuildInfoObservationFilter implements ObservationFilter {

  @Override
  public Observation.Context map(final Observation.Context context) {
    KeyValue buildVersion = KeyValue.of("author", "truc");
    return context.addLowCardinalityKeyValue(buildVersion);
  }
}
