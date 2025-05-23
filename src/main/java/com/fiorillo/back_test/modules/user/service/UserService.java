package com.fiorillo.back_test.modules.user.service;

import com.fiorillo.back_test.modules.user.dto.UserDto;
import com.fiorillo.back_test.modules.user.dto.UserResponse;
import com.fiorillo.back_test.modules.user.model.User;
import com.fiorillo.back_test.modules.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public void create(UserDto dto) {
        userRepository.save(User.of(dto));
    }

    public List<UserResponse> getAll() {
        return userRepository.findAll()
            .stream()
            .map(UserResponse::of)
            .collect(Collectors.toList());
    }
}
