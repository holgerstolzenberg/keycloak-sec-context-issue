package com.ewerk.keycloak.issues;

import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

import org.keycloak.adapters.springsecurity.KeycloakConfiguration;
import org.keycloak.adapters.springsecurity.authentication.KeycloakAuthenticationProvider;
import org.keycloak.adapters.springsecurity.config.KeycloakWebSecurityConfigurerAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.web.authentication.session.NullAuthenticatedSessionStrategy;
import org.springframework.security.web.authentication.session.SessionAuthenticationStrategy;

/**
 * General application wide security configuration. KeyCloak reference documentation holds more
 * details on Spring Boot integration.
 *
 * @see KeycloakConfiguration
 * @see SessionAuthenticationStrategy
 * @see HttpSecurity
 * @see KeycloakWebSecurityConfigurerAdapter
 */
@EnableGlobalMethodSecurity(prePostEnabled = true)
@KeycloakConfiguration
public class SecurityConfiguration extends KeycloakWebSecurityConfigurerAdapter {

  /**
   * Registers the {@code KeycloakAuthenticationProvider} with the authentication manager. The
   * {@code SimpleAuthorityMapper} provides an entry point to map specific resources to the
   * principal.
   */
  @Autowired
  public void configureGlobal(final AuthenticationManagerBuilder auth, final PermissionBasedGrantedAuthorityMapper mapper) {
    final KeycloakAuthenticationProvider provider = keycloakAuthenticationProvider();
    provider.setGrantedAuthoritiesMapper(mapper);

    auth.authenticationProvider(provider);
  }

  /**
   * There is no session in a REST API based application, it is stateless.
   */
  @Override
  protected SessionAuthenticationStrategy sessionAuthenticationStrategy() {
    return new NullAuthenticatedSessionStrategy();
  }

  /**
   * This might be required for Angular CORS pre-flights to function properly.
   *
   * @param web The injected Spring web security instance
   */
  @Override
  public void configure(final WebSecurity web) {
    web.ignoring().antMatchers(HttpMethod.OPTIONS, "/api/**");
  }

  /**
   * Secure appropriate endpoints
   */
  @Override
  protected void configure(final HttpSecurity http) throws Exception {
    super.configure(http);

    //@formatter:off
    http.cors().and()
        .csrf().and()
        .sessionManagement().sessionCreationPolicy(STATELESS).and()
        .formLogin().disable()
        .httpBasic().disable()
        .authorizeRequests()
          .antMatchers("/v2/**").permitAll()
          .antMatchers("/swagger-resources/**").permitAll()
          .antMatchers("/webjars/springfox-swagger-ui/**").permitAll()
          .antMatchers("/swagger-ui.html").permitAll()
          .antMatchers("/actuator/info").permitAll()
          .antMatchers("/actuator/health").permitAll()
          .antMatchers("/actuator/**").fullyAuthenticated()
          .antMatchers("/error").fullyAuthenticated()
          .antMatchers("/api/v1/**").fullyAuthenticated()
          .anyRequest().denyAll();
    //@formatter:on
  }
}
