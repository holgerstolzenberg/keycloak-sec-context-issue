package com.ewerk.keycloak.issues;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@SpringBootApplication
public class Application {

  /**
   * Application entry point
   *
   * @param args Command line arguments
   */
  public static void main(final String... args) {
    SpringApplication.run(Application.class);
  }
}
