package com.ewerk.keycloak.issues;

import org.keycloak.adapters.springboot.KeycloakSpringBootConfigResolver;
import org.keycloak.adapters.springsecurity.config.KeycloakWebSecurityConfigurerAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * This class is normally not necessary. As of {@code KEYCLOAK-11282}, declaring the {@link
 * KeycloakSpringBootConfigResolver} directly in your {@link Configuration} class that extends from
 * {@link KeycloakWebSecurityConfigurerAdapter} will cause the Spring Boot application context not
 * to load.
 *
 * Additional note: As of Keycloak starter version 8.0.0 using this helper there is also no need to
 * override {@code spring.main.allow-bean-definition-overriding} property to {@code true}.
 */
@Configuration
public class SecurityConfigResolverConfiguration {

  SecurityConfigResolverConfiguration() {
  }

  @Bean
  public KeycloakSpringBootConfigResolver keycloakSpringBootConfigResolver() {
    return new KeycloakSpringBootConfigResolver();
  }
}
