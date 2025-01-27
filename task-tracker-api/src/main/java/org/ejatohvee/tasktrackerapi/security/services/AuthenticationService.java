package org.ejatohvee.tasktrackerapi.security.services;

import org.ejatohvee.tasktrackerapi.dtos.requests.NewUserPayload;
import org.ejatohvee.tasktrackerapi.security.entities.JwtRequest;
import org.ejatohvee.tasktrackerapi.security.entities.JwtResponse;
import org.springframework.http.ResponseEntity;

public interface AuthenticationService {
    ResponseEntity<String> registerUser(NewUserPayload payload);

    ResponseEntity<JwtResponse> loginUser(JwtRequest request);
}
