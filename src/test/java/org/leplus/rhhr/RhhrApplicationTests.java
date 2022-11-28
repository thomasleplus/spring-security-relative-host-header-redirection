package org.leplus.rhhr;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class RhhrApplicationTests {

    @Value("${server.port}")
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Bean
    public RestTemplate restTemplate() {
        final RestTemplate restTemplate = new RestTemplate();
        final HttpComponentsClientHttpRequestFactory factory =
                new HttpComponentsClientHttpRequestFactory();
        factory.setHttpClient(HttpClientBuilder.create()
                .disableRedirectHandling().build());
        restTemplate.setRequestFactory(factory);
        return restTemplate;
    }

    @Test
    public void testRedirection() throws Exception {
        assertEquals("/login", restTemplate
                .getForEntity("http://localhost:" + port + "/", String.class)
                .getHeaders().getLocation().toString());
    }

}