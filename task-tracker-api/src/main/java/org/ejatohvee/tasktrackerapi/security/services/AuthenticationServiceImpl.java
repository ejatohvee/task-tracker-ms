package org.ejatohvee.tasktrackerapi.security.services;

import lombok.RequiredArgsConstructor;
import org.ejatohvee.tasktrackerapi.dtos.EmailDto;
import org.ejatohvee.tasktrackerapi.dtos.requests.NewUserPayload;
import org.ejatohvee.tasktrackerapi.kafka.EmailProducer;
import org.ejatohvee.tasktrackerapi.security.CustomUserDetailsManager;
import org.ejatohvee.tasktrackerapi.security.JwtTokenUtils;
import org.ejatohvee.tasktrackerapi.security.entities.JwtRequest;
import org.ejatohvee.tasktrackerapi.security.entities.JwtResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final JwtTokenUtils jwtTokenUtils;
    private final CustomUserDetailsManager userDetailsManager;
    private final AuthenticationManager authenticationManager;
    private final EmailProducer emailProducer;

    @Override
    public ResponseEntity<JwtResponse> loginUser(JwtRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.username(), request.password()));

        UserDetails userDetails = userDetailsManager.loadUserByUsername(request.username());
        String token = jwtTokenUtils.generateToken(userDetails);
        return ResponseEntity.ok(new JwtResponse(token));
    }

    @Override
    public ResponseEntity<String> registerUser(NewUserPayload payload) {
        if (userDetailsManager.findByUsername(payload.username()).isPresent()) {
            return new ResponseEntity<>("User with such name already exists", HttpStatus.CONFLICT);
        }
        if (userDetailsManager.findByEmail(payload.email()).isPresent()) {
            return new ResponseEntity<>("User with such email already exists", HttpStatus.CONFLICT);
        }
        if (!payload.password().equals(payload.confirmPassword())) {
            return new ResponseEntity<>("Passwords don't not match", HttpStatus.CONFLICT);
        }

        userDetailsManager.createUser(payload);

        EmailDto emailDto = new EmailDto(payload.email(), "Registration on ejatohvee.com", "You are successfully registered!");
        emailProducer.sendEmailDto(emailDto);

        return ResponseEntity.ok().build();
    }
}
