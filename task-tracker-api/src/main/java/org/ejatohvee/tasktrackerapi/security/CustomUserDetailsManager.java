package org.ejatohvee.tasktrackerapi.security;

import org.ejatohvee.tasktrackerapi.dtos.requests.NewUserPayload;
import org.ejatohvee.tasktrackerapi.dtos.requests.UpdateUserPayload;
import org.ejatohvee.tasktrackerapi.entities.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Optional;


public interface CustomUserDetailsManager extends UserDetailsService {

    Optional<User> findByUsername(String username);
    Optional<User> findByEmail(String email);
    void updateUser(String username, UpdateUserPayload payload);

    void createUser(NewUserPayload payload);

    UserDetails loadUserByUsername(String username);

    void deleteUser(String username);
}
