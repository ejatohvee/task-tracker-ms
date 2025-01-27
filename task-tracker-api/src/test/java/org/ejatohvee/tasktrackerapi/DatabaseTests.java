package org.ejatohvee.tasktrackerapi;

import org.ejatohvee.tasktrackerapi.entities.Task;
import org.ejatohvee.tasktrackerapi.entities.User;
import org.ejatohvee.tasktrackerapi.repositories.TaskRepository;
import org.ejatohvee.tasktrackerapi.repositories.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@SpringBootTest
@ActiveProfiles("test")
@Sql(value = "classpath:sql/initDB.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(value = "classpath:sql/clearDB.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
public class DatabaseTests {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TaskRepository taskRepository;

    @Test
    void FindWithAuthoritiesById_getUsersAuthorities_Success() {
        long userId = 1;

        User user = userRepository.findWithAuthoritiesById(userId).orElseThrow(() -> new NoSuchElementException("User with such ID is not found"));
        int authoritiesCount = user.getAuthorities().size();

        int expected = 2;

        Assertions.assertEquals(expected, authoritiesCount);
    }

    @Test
    void FindTasksByOwner_GetUsersTasks_Success() {
        long userId = 1;

        User user = userRepository.findById(userId).orElseThrow(() -> new NoSuchElementException("Task with such ID is not found"));
        List<Task> taskList = taskRepository.findTasksByOwner(user);
        for(Task task : taskList) System.out.print(task.getOwner().getUsername() + '\n');

        Assertions.assertNotNull(taskList);
    }

    @Test
    void FindTasksByOwnerIdIn_GetTasksByOwners_Success() {
        List<Long> userIds = new ArrayList<>();
        userIds.add(1L);
        userIds.add(2L);
        userIds.add(3L);

        Page<Task> taskList = taskRepository.findTasksByOwnerIdIn(userIds, PageRequest.of(0, 10));

        List<Long> expectedIds = new ArrayList<>();
        expectedIds.add(1L);
        expectedIds.add(2L);
        expectedIds.add(3L);

        Assertions.assertEquals(taskList.stream().map(task -> task.getId()).collect(Collectors.toList()), expectedIds);
    }
}
