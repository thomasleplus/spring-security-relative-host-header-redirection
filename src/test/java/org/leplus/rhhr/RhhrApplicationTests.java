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

@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT)
public class RhhrApplicationTests {

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
                .getForEntity("http://localhost:8080/", String.class)
                .getHeaders().getLocation().toString());
    }

}