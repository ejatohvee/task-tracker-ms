package org.ejatohvee.tasktrackerscheduler.dtos;

import java.sql.Timestamp;

public record TaskDto(Long id, String title, String description, boolean isDone, Timestamp isDoneTime, String username) {
}
