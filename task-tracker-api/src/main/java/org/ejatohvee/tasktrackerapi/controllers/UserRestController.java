package org.ejatohvee.tasktrackerapi.controllers;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.ejatohvee.tasktrackerapi.dtos.requests.UpdateUserPayload;
import org.ejatohvee.tasktrackerapi.security.CustomUserDetailsManager;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("api/user/{username}")
@RequiredArgsConstructor
public class UserRestController {
    private final CustomUserDetailsManager userDetailsManager;

    @PatchMapping
    @PreAuthorize("hasAuthority('ROLE_ADMIN') or authentication.name == #username")
    public ResponseEntity<Void> updateUser(@PathVariable String username, @RequestBody @Valid UpdateUserPayload payload) {
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        System.out.println("Authenticated username: " + name);
        userDetailsManager.updateUser(username, payload);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping
    @PreAuthorize("hasAuthority('ROLE_ADMIN') or authentication.name == #username")
    public ResponseEntity<Void> deleteUser(@PathVariable String username) {
        userDetailsManager.deleteUser(username);

        return ResponseEntity.noContent().build();
    }
}
