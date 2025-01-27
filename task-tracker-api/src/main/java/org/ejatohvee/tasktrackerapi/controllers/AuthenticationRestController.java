package org.ejatohvee.tasktrackerapi.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.ejatohvee.tasktrackerapi.dtos.requests.NewUserPayload;
import org.ejatohvee.tasktrackerapi.security.entities.JwtRequest;
import org.ejatohvee.tasktrackerapi.security.entities.JwtResponse;
import org.ejatohvee.tasktrackerapi.security.services.AuthenticationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/auth")
@RequiredArgsConstructor
public class AuthenticationRestController {
    private final AuthenticationService authenticationService;

    @PostMapping("/login")
    public ResponseEntity<JwtResponse> loginUser(@RequestBody JwtRequest request) {
        return authenticationService.loginUser(request);
    }

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody @Valid NewUserPayload payload) {
        return authenticationService.registerUser(payload);
    }
}
