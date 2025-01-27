package org.ejatohvee.tasktrackerapi.services;


import org.ejatohvee.tasktrackerapi.dtos.requests.NewTaskPayload;
import org.ejatohvee.tasktrackerapi.dtos.requests.UpdateTaskPayload;
import org.ejatohvee.tasktrackerapi.dtos.responses.TaskResponse;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TaskService {
    TaskResponse createTask(NewTaskPayload payload, String username);

    TaskResponse findTaskById(long id);

    void updateTask(long taskId, String username, UpdateTaskPayload payload);

    void deleteTask(long taskId, String username);

    List<TaskResponse> getAllUserTasks(long userId);

    List<TaskResponse> getAllUsersTasksBatch(List<Long> ids, Pageable paging);
}
