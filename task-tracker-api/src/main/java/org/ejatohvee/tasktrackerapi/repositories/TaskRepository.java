package org.ejatohvee.tasktrackerapi.repositories;

import org.ejatohvee.tasktrackerapi.entities.Task;
import org.ejatohvee.tasktrackerapi.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends CrudRepository<Task, Long> {

    @EntityGraph(attributePaths = {"owner"})
    List<Task> findTasksByOwner(User owner);

    @EntityGraph(attributePaths = {"owner"})
    Page<Task> findTasksByOwnerIdIn(List<Long> ids, Pageable paging);
}
