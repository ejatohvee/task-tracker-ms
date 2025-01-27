package org.ejatohvee.tasktrackerapi.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.ejatohvee.tasktrackerapi.dtos.requests.UpdateTaskPayload;
import org.ejatohvee.tasktrackerapi.dtos.responses.TaskResponse;
import org.ejatohvee.tasktrackerapi.services.TaskService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/task/{id}")
public class TaskRestController {
    private final TaskService taskService;

    @GetMapping
    public ResponseEntity<TaskResponse> getTask(@PathVariable long id) {
        return ResponseEntity.ok(taskService.findTaskById(id));
    }

    @PatchMapping
    public ResponseEntity<Void> updateTask(@PathVariable long id, @RequestBody @Valid UpdateTaskPayload payload, @AuthenticationPrincipal String username) {
        taskService.updateTask(id, username, payload);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteTask(@PathVariable long id, @AuthenticationPrincipal String username) {
        taskService.deleteTask(id, username);
        return ResponseEntity.noContent().build();
    }
}
