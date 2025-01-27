package org.ejatohvee.tasktrackerapi.services;


import org.ejatohvee.tasktrackerapi.dtos.responses.UserResponse;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserService {
    UserResponse getById(Long id);

    List<UserResponse> getAllUsers(Pageable paging);
}
