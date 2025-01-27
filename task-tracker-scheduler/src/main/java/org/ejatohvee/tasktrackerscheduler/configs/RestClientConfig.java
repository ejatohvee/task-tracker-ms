package org.ejatohvee.tasktrackerscheduler.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestClient;


@Configuration
public class RestClientConfig {
    @Bean
    public RestClient restClient() {
        return RestClient
                .builder()
                .requestFactory(getClientHttpRequestFactory())
                .build();
    }

    private ClientHttpRequestFactory getClientHttpRequestFactory() {
        SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
        factory.setReadTimeout(6000);
        factory.setConnectTimeout(6000);
        return factory;
    }
}
