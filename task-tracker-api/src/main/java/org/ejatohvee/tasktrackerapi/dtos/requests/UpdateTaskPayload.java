package org.ejatohvee.tasktrackerapi.dtos.requests;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record UpdateTaskPayload(
                                @Size(min = 1, max = 50, message = "{catalogue.tasks.create.errors.title.size}")
                                String title,
                                @Size(max = 5000, message = "{catalogue.tasks.update.errors.description.size}")
                                String description,
                                @NotNull(message = "{catalogue.tasks.update.errors.isDone.not_blank}")
                                boolean isDone) {
}
