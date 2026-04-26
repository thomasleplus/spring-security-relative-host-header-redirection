package org.leplus.rhhr;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;

/** The web security configuration. */
@EnableWebSecurity
public class SecurityConfig extends AbstractHttpConfigurer<SecurityConfig, HttpSecurity> {

  /** The default constructor. */
  public SecurityConfig() {
    super();
  }

  /**
   * The filter chain.
   *
   * @param http the HttpSecurity to configure.
   * @return the configured chain.
   * @throws Exception if somethings goes wrong.
   */
  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    final LoginUrlAuthenticationEntryPoint entryPoint =
        new LoginUrlAuthenticationEntryPoint("/login");
    entryPoint.setFavorRelativeUris(true);
    http.authorizeHttpRequests(authz -> authz.anyRequest().authenticated())
        .formLogin(formLogin -> formLogin.loginPage("/login").permitAll())
        .exceptionHandling(ex -> ex.authenticationEntryPoint(entryPoint));
    return http.getOrBuild();
  }
}
