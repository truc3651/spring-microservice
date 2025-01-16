package com.example.composite.configs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties("api")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiDocument {
  private String version;
  private String title;
  private String description;
  private String termsOfService;
  private String license;
  private String licenseUrl;
  private String externalDocDesc;
  private String externalDocUrl;
  @NestedConfigurationProperty
  private Contact contact;

  @Data
  @AllArgsConstructor
  @NoArgsConstructor
  public static class Contact {
    private String name;
    private String url;
    private String email;
  }
}
