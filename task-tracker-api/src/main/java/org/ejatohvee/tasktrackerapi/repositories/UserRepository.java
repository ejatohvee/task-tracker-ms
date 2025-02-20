package org.ejatohvee.tasktrackerapi.repositories;


import org.ejatohvee.tasktrackerapi.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
    Optional<User> findByEmail(String email);

    @EntityGraph(attributePaths = {"authorities"})
    Optional<User> findWithAuthoritiesById(Long id);
    boolean existsByUsername(String username);

    boolean existsByEmail(String email);

    Page<User> findAll(Pageable paging);
}
