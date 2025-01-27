package org.ejatohvee.tasktrackerapi.dtos.responses;

import java.sql.Timestamp;

public record TaskResponse(Long id, String title, String description, boolean isDone, Timestamp isDoneTime, String username) {
}
