package org.ejatohvee.tasktrackerapi.dtos.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record NewTaskPayload(@NotBlank(message = "{catalogue.tasks.create.errors.title.not_blank}")
                             @Size(max = 50, message = "{catalogue.tasks.create.errors.title.size}")
                             String title,
                             @Size(max = 5000, message = "{catalogue.tasks.create.errors.description.size}")
                             String description) {
}
