package org.leplus.rhhr;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URI;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;
import org.springframework.security.web.util.UrlUtils;

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
    entryPoint.setRedirectStrategy(new RelativeRedirectStrategy());
    http.authorizeHttpRequests(authz -> authz.anyRequest().authenticated())
        .formLogin(formLogin -> formLogin.loginPage("/login").permitAll())
        .exceptionHandling(ex -> ex.authenticationEntryPoint(entryPoint));
    return http.getOrBuild();
  }

  /**
   * A redirect strategy that always sends a relative (path-only) Location header, preventing
   * Host Header Injection attacks where a spoofed Host header would otherwise redirect users to an
   * attacker-controlled domain.
   */
  private static final class RelativeRedirectStrategy extends DefaultRedirectStrategy {

    @Override
    public void sendRedirect(
        final HttpServletRequest request, final HttpServletResponse response, final String url)
        throws IOException {
      final String redirectUrl;
      if (UrlUtils.isAbsoluteUrl(url)) {
        final URI uri = URI.create(url);
        final String query = uri.getQuery();
        redirectUrl = query != null ? uri.getPath() + "?" + query : uri.getPath();
      } else {
        redirectUrl = url;
      }
      response.setHeader("Location", redirectUrl);
      response.setStatus(HttpServletResponse.SC_FOUND);
    }
  }
}
