package org.ejatohvee.tasktrackerapi.security.services;

import lombok.RequiredArgsConstructor;
import org.ejatohvee.tasktrackerapi.entities.Authority;
import org.ejatohvee.tasktrackerapi.repositories.AuthorityRepository;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class AuthoritiesServiceImpl implements AuthoritiesService {
    private final AuthorityRepository authorityRepository;

    @Override
    public Authority getUserAuthority() {
        return authorityRepository.findByAuthority("ROLE_USER").orElseThrow(() -> new RuntimeException("Authority for this user not found in the database"));
    }
}
