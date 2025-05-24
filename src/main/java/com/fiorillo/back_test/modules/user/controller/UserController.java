package com.fiorillo.back_test.modules.user.controller;

import com.fiorillo.back_test.modules.user.dto.UserDto;
import com.fiorillo.back_test.modules.user.dto.UserResponse;
import com.fiorillo.back_test.modules.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/user")
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody UserDto userDto) {
        return userService.create(userDto);
    }

    @GetMapping
    public List<UserResponse> getAll() {
        return userService.getAll();
    }
}
