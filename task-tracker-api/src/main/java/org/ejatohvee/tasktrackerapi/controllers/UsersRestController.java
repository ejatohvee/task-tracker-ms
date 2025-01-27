package org.ejatohvee.tasktrackerapi.controllers;

import lombok.RequiredArgsConstructor;
import org.ejatohvee.tasktrackerapi.dtos.responses.UserResponse;
import org.ejatohvee.tasktrackerapi.services.UserService;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/users")
@RequiredArgsConstructor
public class UsersRestController {
    private final UserService userService;

    @GetMapping
    @PreAuthorize("hasAuthority('ROLE_SERVICE')")
    public ResponseEntity<List<UserResponse>> getAllUsersBatch(
            @RequestParam int page, @RequestParam int size
    ) {
        return ResponseEntity.ok(userService.getAllUsers(PageRequest.of(page, size)));
    }
}
