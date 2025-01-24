package com.example.gateway;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity.CsrfSpec;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {

  private static final Logger LOG = LoggerFactory.getLogger(SecurityConfig.class);

  @Bean
  public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) {
    return http
        .csrf(CsrfSpec::disable) // Updated way to disable CSRF
        .authorizeExchange(exchanges -> exchanges
            .pathMatchers(
                "/headerrouting/**",
                "/actuator/**",
                "/eureka/**",
                "/oauth2/**",
                "/login/**",
                "/error/**",
                "/openapi/**",
                "/webjars/**",
                "/config/**"
            ).permitAll() // Allow access to these paths without authentication
            .anyExchange().authenticated() // Require authentication for all other paths
        )
        .oauth2ResourceServer(oauth2 -> oauth2
            .jwt(jwt -> {}) // Enable JWT-based OAuth2 resource server
        )
        .build();
  }
}
