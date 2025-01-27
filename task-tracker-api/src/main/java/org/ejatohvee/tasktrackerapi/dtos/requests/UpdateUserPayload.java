package org.ejatohvee.tasktrackerapi.dtos.requests;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.Size;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record UpdateUserPayload(
        @Size(min = 3, max = 20, message = "{users.update.errors.username.wrong_username_length}") String username,
        String email,
        @Size(min = 5, message = "{users.update.errors.password.size}") String password) {
}
