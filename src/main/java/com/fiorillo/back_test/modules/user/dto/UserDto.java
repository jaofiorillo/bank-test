package com.fiorillo.back_test.modules.user.dto;

import jakarta.validation.constraints.NotEmpty;

public record UserDto(@NotEmpty String username, @NotEmpty String password) {
}
