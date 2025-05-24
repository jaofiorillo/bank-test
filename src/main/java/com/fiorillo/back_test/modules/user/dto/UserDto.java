package com.fiorillo.back_test.modules.user.dto;

import jakarta.validation.constraints.NotBlank;

public record UserDto(@NotBlank String userName, @NotBlank String password) {
}
