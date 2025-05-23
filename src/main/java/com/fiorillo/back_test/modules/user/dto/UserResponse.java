package com.fiorillo.back_test.modules.user.dto;

import com.fiorillo.back_test.modules.user.model.User;

public record UserResponse(Integer id, String username) {

    public static UserResponse of(User user) {
        return new UserResponse(
            user.getId(),
            user.getUserName());
    }
}
