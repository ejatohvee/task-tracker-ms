package org.ejatohvee.tasktrackerapi.dtos.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record NewUserPayload(
        @Size(min = 3, max = 20, message = "{api.auth.register.errors.username.size}") String username,
        @NotBlank(message = "{api.auth.register.errors.email.not_blank}") String email,
        @Size(min = 5, message = "{api.auth.register.errors.password.size}") String password,
        @NotBlank(message = "{api.auth.register.errors.confirmationPassword.not_blank}") String confirmPassword) {
}
