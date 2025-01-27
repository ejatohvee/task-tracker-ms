package org.ejatohvee.tasktrackerscheduler.clients;

import lombok.RequiredArgsConstructor;
import org.ejatohvee.tasktrackerscheduler.dtos.TaskDto;
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
public class TaskServiceClient {
    private final RestClient restClient;

    @Value("${external.tasks-url}")
    private String tasksUrl;

    public List<TaskDto> getUsersTasks(long userId, String token) {
        String url = UriComponentsBuilder
                .fromUriString(tasksUrl)
                .path("/{userId}")
                .buildAndExpand(userId)
                .toUriString();

        return restClient.get()
                .uri(url)
                .header("Authorization", "Bearer " + token)
                .accept(MediaType.APPLICATION_JSON)
                .exchange((request, response) -> {
                    if (response.getStatusCode().is4xxClientError() || response.getStatusCode().is5xxServerError()) {
                        throw new ClientRuntimeException(response.getStatusCode(), response.bodyTo(String.class));
                    } else {
                        return response.bodyTo(new ParameterizedTypeReference<List<TaskDto>>() {
                        });
                    }
                });
    }
}
