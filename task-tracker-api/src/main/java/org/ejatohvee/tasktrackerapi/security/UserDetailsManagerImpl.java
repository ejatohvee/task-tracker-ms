package org.ejatohvee.tasktrackerapi.security;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.ejatohvee.tasktrackerapi.dtos.requests.NewUserPayload;
import org.ejatohvee.tasktrackerapi.dtos.requests.UpdateUserPayload;
import org.ejatohvee.tasktrackerapi.entities.User;
import org.ejatohvee.tasktrackerapi.repositories.UserRepository;
import org.ejatohvee.tasktrackerapi.security.entities.SecurityUser;
import org.ejatohvee.tasktrackerapi.security.services.AuthoritiesService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserDetailsManagerImpl implements CustomUserDetailsManager {
    private final UserRepository userRepository;
    private final AuthoritiesService authoritiesService;
    private final PasswordEncoderConfig passwordEncoderConfig;

    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = findByUsername(username).orElseThrow(() ->
                new UsernameNotFoundException("User %s not found.".formatted(username)));
        return new SecurityUser(user);
    }

    @Transactional
    public void createUser(NewUserPayload payload) {
        User user = new User();
        user.setUsername(payload.username());
        user.setEmail(payload.email());
        user.setPassword(passwordEncoderConfig.passwordEncoder().encode(payload.password()));
        user.setAuthorities(List.of(authoritiesService.getUserAuthority()));
        userRepository.save(user);
    }

    @Transactional
    public void updateUser(String username, UpdateUserPayload payload) {
        User user = userRepository.findByUsername(username).orElseThrow(() ->
                new UsernameNotFoundException("User %s not found".formatted(username))
        );

        if (payload.username() != null && !payload.username().trim().isEmpty()) {
            user.setUsername(payload.username());
        }

        if (payload.email() != null && !payload.email().trim().isEmpty()) {
            user.setEmail(payload.email());
        }

        if (payload.password() != null && !payload.password().trim().isEmpty()) {
            user.setPassword(passwordEncoderConfig.passwordEncoder().encode(payload.password()));
        }

        userRepository.save(user);
    }

    @Transactional
    public void deleteUser(String username) {
        SecurityUser user = (SecurityUser) loadUserByUsername(username);
        userRepository.deleteById(user.getId());
    }
}
