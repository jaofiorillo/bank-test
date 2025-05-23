package com.fiorillo.back_test.modules.user.model;

import com.fiorillo.back_test.modules.user.dto.UserDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.persistence.*;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "USERS")
public class User {

    @Id
    @SequenceGenerator(name = "SEQ_USER", sequenceName = "SEQ_USER", allocationSize = 1)
    @GeneratedValue(generator = "SEQ_USER", strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "USER_NAME")
    private String userName;

    @Column(name = "PASSWORD")
    private String password;

    public static User of(UserDto dto) {
        var user = new User();
        user.setUserName(dto.username());
        user.setPassword(dto.password());
        return user;
    }
}
