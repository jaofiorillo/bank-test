package com.fiorillo.back_test.modules.account.model;

import com.fiorillo.back_test.modules.account.dto.AccountDto;
import com.fiorillo.back_test.modules.user.dto.UserDto;
import com.fiorillo.back_test.modules.user.model.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "ACCOUNT")
public class Account {

    @Id
    @SequenceGenerator(name = "SEQ_ACCOUNT", sequenceName = "SEQ_ACCOUNT", allocationSize = 1)
    @GeneratedValue(generator = "SEQ_ACCOUNT", strategy = GenerationType.AUTO)
    Integer id;

    @Column(name = "NUMBER_ACCOUNT")
    private Integer numberAccount;

    @Column(name = "BALANCE")
    private BigDecimal balance;

    @JoinColumn(name = "FK_USER", referencedColumnName = "ID", foreignKey = @ForeignKey(name = "FK_USER_ACCOUNT"))
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    public static Account of(AccountDto dto, User user) {
        var account = new Account();
        account.setNumberAccount(dto.numberAccount());
        account.setBalance(dto.balence());
        account.setUser(user);
        return account;
    }
}
