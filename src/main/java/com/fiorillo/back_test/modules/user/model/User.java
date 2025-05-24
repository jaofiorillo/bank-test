package com.fiorillo.back_test.modules.user.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fiorillo.back_test.modules.account.model.Account;
import com.fiorillo.back_test.modules.user.dto.UserDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.persistence.*;
import java.util.List;
import java.util.stream.Collectors;

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

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<Account> accounts;

    public static User of(UserDto dto) {
        var user = new User();
        user.setUserName(dto.userName());
        user.setPassword(dto.password());
        return user;
    }

    @JsonIgnore
    public List<Integer> getNumberAccounts() {
        return accounts != null
            ? accounts.stream()
            .map(Account::getNumberAccount)
            .collect(Collectors.toList())
            : List.of();
    }
}
