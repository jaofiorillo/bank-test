package com.fiorillo.back_test.modules.user.service;

import com.fiorillo.back_test.modules.user.dto.UserDto;
import com.fiorillo.back_test.modules.user.dto.UserResponse;
import com.fiorillo.back_test.modules.user.model.User;
import com.fiorillo.back_test.modules.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public ResponseEntity<?> create(UserDto dto) {
        try {
            var user = User.of(dto);
            user.setPassword(passwordEncoder.encode(dto.password()));
            userRepository.save(user);

            return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(UserResponse.of(user));
        } catch (Exception ex) {
            return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(Map.of("message", "Erro ao criar usuário"));
        }
    }

    public List<UserResponse> getAll() {
        return userRepository.findAll()
            .stream()
            .map(UserResponse::of)
            .collect(Collectors.toList());
    }
}
