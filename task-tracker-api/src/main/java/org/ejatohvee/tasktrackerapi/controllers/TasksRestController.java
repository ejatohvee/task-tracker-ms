package org.ejatohvee.tasktrackerapi.controllers;

import lombok.RequiredArgsConstructor;
import org.ejatohvee.tasktrackerapi.dtos.requests.NewTaskPayload;
import org.ejatohvee.tasktrackerapi.dtos.responses.TaskResponse;
import org.ejatohvee.tasktrackerapi.services.TaskService;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("api/tasks")
public class TasksRestController {
    private final TaskService taskService;

    @PostMapping("/create")
    public ResponseEntity<TaskResponse> createTask(@RequestBody NewTaskPayload payload, @AuthenticationPrincipal String username) {

        return ResponseEntity.ok(taskService.createTask(payload, username));
    }

    @GetMapping
    public ResponseEntity<List<TaskResponse>> getAllUsersTasksBatch(
            @RequestParam int page, @RequestParam int size, @RequestBody List<Long> usersIds
    ) {
        return ResponseEntity.ok(taskService.getAllUsersTasksBatch(usersIds, PageRequest.of(page, size)));
    }

    @GetMapping("{userId}")
    public ResponseEntity<List<TaskResponse>> getUsersTasks(@PathVariable(name = "userId") long userId) {
        return ResponseEntity.ok(taskService.getAllUserTasks(userId));
    }
}
