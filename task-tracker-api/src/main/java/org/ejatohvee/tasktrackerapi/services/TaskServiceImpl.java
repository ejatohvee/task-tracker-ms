package org.ejatohvee.tasktrackerapi.services;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.ejatohvee.tasktrackerapi.dtos.requests.NewTaskPayload;
import org.ejatohvee.tasktrackerapi.dtos.requests.UpdateTaskPayload;
import org.ejatohvee.tasktrackerapi.dtos.responses.TaskResponse;
import org.ejatohvee.tasktrackerapi.entities.Task;
import org.ejatohvee.tasktrackerapi.entities.User;
import org.ejatohvee.tasktrackerapi.exceptions.TaskOwnershipException;
import org.ejatohvee.tasktrackerapi.mapper.TaskMapper;
import org.ejatohvee.tasktrackerapi.repositories.TaskRepository;
import org.ejatohvee.tasktrackerapi.repositories.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {
    private final TaskMapper mapper;
    private final TaskRepository taskRepository;
    private final UserRepository userRepository;

    @Override
    public TaskResponse findTaskById(long id) {
        Task task = taskRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Task with such ID is not found"));

        return mapper.toTaskResponse(task);
    }

    @Override
    public TaskResponse createTask(NewTaskPayload payload, String username) {
        User user = userRepository.findByUsername(username).orElseThrow(() -> new NoSuchElementException("User with such ID is not found"));
        Task task = mapper.toTask(payload, false, null, user);

        Task saved = taskRepository.save(task);

        return mapper.toTaskResponse(saved);
    }

    @Override
    @Transactional
    public void updateTask(long taskId, String username, UpdateTaskPayload payload) {
        Task task = taskRepository.findById(taskId).orElseThrow(() -> new NoSuchElementException("Task with such ID is not found"));
        checkOwnership(taskId, username);

        if(payload.title() != null) task.setTitle(payload.title());
        if(payload.description() != null) task.setDescription(payload.description());

        if (!task.isDone() && payload.isDone()) {
            task.setDone(true);
            task.setIsDoneTime(Timestamp.valueOf(LocalDateTime.now()));
        } else if (task.isDone() && !payload.isDone()) {
            task.setDone(false);
        }
    }

    @Override
    public void deleteTask(long taskId, String username) {
        checkOwnership(taskId, username);

        taskRepository.deleteById(taskId);
    }

    @Override
    public List<TaskResponse> getAllUserTasks(long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new NoSuchElementException("User with such ID is not found"));
        List<Task> tasks = taskRepository.findTasksByOwner(user);

        return tasks.stream().map(task -> mapper.toTaskResponse(task)).toList();
    }

    @Override
    public List<TaskResponse> getAllUsersTasksBatch(List<Long> ids, Pageable paging) {
        Page<Task> page = taskRepository.findTasksByOwnerIdIn(ids, paging);

        return page.stream().map(task -> mapper.toTaskResponse(task)).toList();
    }


    private void checkOwnership(long taskId, String username) {
        if (!Objects.equals(findTaskById(taskId).username(), username)) throw new TaskOwnershipException("Task doesn't belong to user");
    }
}
