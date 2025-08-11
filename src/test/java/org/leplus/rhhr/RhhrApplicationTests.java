package org.leplus.rhhr;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.apache.hc.client5.http.impl.classic.HttpClientBuilder;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

/** Tests the RhhrApplication. */
@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT)
public class RhhrApplicationTests {

  /** The default constructor. */
  public RhhrApplicationTests() {
    super();
  }

  /** The rest template. */
  @Autowired private TestRestTemplate restTemplate;

  /**
   * Configures the rest template.
   *
   * @return the configured rest template.
   */
  @Bean
  public RestTemplate restTemplate() {
    final RestTemplate restTemplate = new RestTemplate();
    final HttpComponentsClientHttpRequestFactory factory =
        new HttpComponentsClientHttpRequestFactory();
    factory.setHttpClient(HttpClientBuilder.create().disableRedirectHandling().build());
    restTemplate.setRequestFactory(factory);
    return restTemplate;
  }

  /**
   * Tests the redirection.
   *
   * @throws Exception if the test fails.
   */
  @Test
  public void testRedirection() throws Exception {
    assertEquals(
        "/login",
        restTemplate
            .getForEntity("http://localhost:8080/", String.class)
            .getHeaders()
            .getLocation()
            .toString());
  }
}
