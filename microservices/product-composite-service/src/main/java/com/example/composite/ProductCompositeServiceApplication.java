package com.example.composite;

import com.example.composite.configs.ApiDocument;
import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@ComponentScan({"com.example"})
public class ProductCompositeServiceApplication {
  public static void main(String[] args) {
    SpringApplication.run(ProductCompositeServiceApplication.class, args);
  }

  @LoadBalanced
  @Bean
  public RestTemplate restTemplate() {
    return new RestTemplate();
  }

  @Bean
  public OpenAPI getOpenApiDocumentation(ApiDocument apiDocument) {
    ApiDocument.Contact contact = apiDocument.getContact();

    return new OpenAPI()
        .info(new Info().title(apiDocument.getTitle())
            .description(apiDocument.getDescription())
            .version(apiDocument.getVersion())
            .contact(new Contact()
                .name(contact.getName())
                .url(contact.getUrl())
                .email(contact.getEmail()))
            .termsOfService(apiDocument.getTermsOfService())
            .license(new License()
                .name(apiDocument.getLicense())
                .url(apiDocument.getLicenseUrl())))
        .externalDocs(new ExternalDocumentation()
            .description(apiDocument.getExternalDocDesc())
            .url(apiDocument.getExternalDocUrl()));
  }
}
