package org.ejatohvee.tasktrackerapi.repositories;


import org.ejatohvee.tasktrackerapi.entities.Authority;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuthorityRepository extends CrudRepository<Authority, Long> {
    Optional<Authority> findByAuthority(String authority);
}
