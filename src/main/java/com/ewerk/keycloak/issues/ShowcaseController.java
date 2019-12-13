package com.ewerk.keycloak.issues;

import static java.util.UUID.randomUUID;
import static org.slf4j.LoggerFactory.getLogger;

import org.keycloak.AuthorizationContext;
import org.keycloak.KeycloakPrincipal;
import org.keycloak.KeycloakSecurityContext;
import org.slf4j.Logger;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("${api.v1.allowed-origins}")
@RequestMapping("${api.v1.base-path}/showcase")
public class ShowcaseController {

  private static final Logger LOG = getLogger(ShowcaseController.class);

  ShowcaseController() {
  }

  @PreAuthorize("hasAuthority('p:hello-world-resource:read')")
  @GetMapping(path = "/hello", produces = "text/plain")
  public String hello() {
    final KeycloakSecurityContext keycloakSecurityContext = ((KeycloakPrincipal<?>) SecurityContextHolder
        .getContext()
        .getAuthentication()
        .getPrincipal())
        .getKeycloakSecurityContext();

    // not working
    final AuthorizationContext authorizationContext = keycloakSecurityContext
        .getAuthorizationContext();
    LOG.error("Authorization context: {}", authorizationContext);

    return randomUUID().toString();
  }
}
