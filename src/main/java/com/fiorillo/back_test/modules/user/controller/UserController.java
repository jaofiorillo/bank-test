package com.fiorillo.back_test.modules.user.controller;

import com.fiorillo.back_test.modules.user.dto.UserDto;
import com.fiorillo.back_test.modules.user.dto.UserResponse;
import com.fiorillo.back_test.modules.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    public void create(@RequestBody UserDto userDto) {
        userService.create(userDto);
    }

    @GetMapping
    public List<UserResponse> getAll() {
        return userService.getAll();
    }
}
