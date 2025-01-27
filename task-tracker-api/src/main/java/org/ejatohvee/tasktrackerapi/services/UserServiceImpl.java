package org.ejatohvee.tasktrackerapi.services;

import lombok.RequiredArgsConstructor;
import org.ejatohvee.tasktrackerapi.dtos.responses.UserResponse;
import org.ejatohvee.tasktrackerapi.entities.User;
import org.ejatohvee.tasktrackerapi.mapper.UserMapper;
import org.ejatohvee.tasktrackerapi.repositories.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserMapper mapper;
    private final UserRepository userRepository;
    @Override
    public UserResponse getById(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new NoSuchElementException("User with such ID is not found"));

        return mapper.toUserResponse(user);
    }

    @Override
    public List<UserResponse> getAllUsers(Pageable paging) {
        Page<User> page = userRepository.findAll(paging);

        return page.stream().map(user -> mapper.toUserResponse(user)).toList();
    }
}
