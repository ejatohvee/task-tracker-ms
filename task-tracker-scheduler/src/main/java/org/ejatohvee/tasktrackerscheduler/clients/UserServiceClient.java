package org.ejatohvee.tasktrackerscheduler.clients;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.ejatohvee.tasktrackerscheduler.dtos.UserDto;
import org.ejatohvee.tasktrackerscheduler.exceptions.ClientRuntimeException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceClient {
    private final RestClient restClient;

    @Value("${external.users-url}")
    private String usersUrl;
    @Value("${spring.data.rest.max-page-size}")
    private int pageSize;

    public List<UserDto> getAllUsersBatch(int page, String token) {
        String url = UriComponentsBuilder
                .fromUriString(usersUrl)
                .queryParam("page", page)
                .queryParam("size", pageSize)
                .toUriString();

        return restClient.get()
                .uri(url)
                .header("Authorization", "Bearer " + token)
                .accept(MediaType.APPLICATION_JSON)
                .exchange((request, response) -> {
                    if (response.getStatusCode().is4xxClientError() || response.getStatusCode().is5xxServerError()) {
                        throw new ClientRuntimeException(response.getStatusCode(), response.bodyTo(String.class));
                    } else {
                        return response.bodyTo(new ParameterizedTypeReference<List<UserDto>>() {
                        });
                    }
                });
    }

}
