package org.leplus.rhhr;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClientBuilder;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.server.LocalServerPort;

/** Tests the RhhrApplication. */
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class RhhrApplicationTests {

  /** The local server port. */
  @LocalServerPort private int port;

  /** The default constructor. */
  public RhhrApplicationTests() {
    super();
  }

  /**
   * Tests the redirection.
   *
   * @throws Exception if the test fails.
   */
  @Test
  public void testRedirection() throws Exception {
    try (CloseableHttpClient client =
        HttpClientBuilder.create().disableRedirectHandling().build()) {
      client.execute(
          new HttpGet("http://localhost:" + port + "/"),
          response -> {
            final var locationHeader = response.getFirstHeader("Location");
            assertNotNull(locationHeader, "Location header is missing");
            assertEquals("/login", locationHeader.getValue());
            return null;
          });
    }
  }
}
