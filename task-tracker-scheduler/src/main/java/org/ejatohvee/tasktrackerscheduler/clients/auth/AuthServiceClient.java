package org.ejatohvee.tasktrackerscheduler.clients.auth;

import lombok.RequiredArgsConstructor;
import org.ejatohvee.tasktrackerscheduler.exceptions.ClientRuntimeException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class AuthServiceClient {
    @Value("${external.token-url}")
    private String tokenUrl;
    private final AuthUser authUser;

    private final RestClient restClient;

    public String getToken() {
        return restClient.post()
                .uri(tokenUrl)
                .contentType(MediaType.APPLICATION_JSON)
                .body(authUser)
                .accept(MediaType.APPLICATION_JSON)
                .exchange((request, response) -> {
                    if (response.getStatusCode().is4xxClientError() || response.getStatusCode().is5xxServerError()) {
                        throw new ClientRuntimeException(response.getStatusCode(), response.bodyTo(String.class));
                    } else {
                        return convertResponse(Objects.requireNonNull(response.bodyTo(String.class)));
                    }
                });
    }

    private String convertResponse(String body) {
        if (body.contains("token")) {
            return body.substring(10, body.length()-2);
        } else {
            throw new IllegalArgumentException("Unexpected token format: " + body);
        }
    }
}
