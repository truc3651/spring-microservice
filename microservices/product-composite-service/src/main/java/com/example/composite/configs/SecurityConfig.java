package com.example.composite.configs;

import static org.springframework.http.HttpMethod.DELETE;
import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpMethod.POST;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    return http
        .authorizeHttpRequests(
            auth -> auth
                .requestMatchers(
                    "/actuator/**",
                    "/openapi/**",
                    "/webjars/**")
                .permitAll()
                .requestMatchers(POST, "/product-composite/**")
                .hasAuthority("SCOPE_product:write")
                .requestMatchers(DELETE, "/product-composite/**")
                .hasAuthority("SCOPE_product:write")
                .requestMatchers(GET, "/product-composite/**")
                .hasAuthority("SCOPE_product:read")
                .anyRequest().authenticated()
        )
        .oauth2ResourceServer(
            oauth2 -> oauth2
                .jwt(jwt -> {})
        )
        .build();
  }
}

