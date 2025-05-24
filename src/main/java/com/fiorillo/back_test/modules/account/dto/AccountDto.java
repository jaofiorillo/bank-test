package com.fiorillo.back_test.modules.account.dto;

import com.fiorillo.back_test.modules.account.model.Account;

import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;

public record AccountDto(@NotNull Integer numberAccount, @NotNull BigDecimal balence) {

    public static AccountDto of(Account account) {
        return new AccountDto(
            account.getNumberAccount(),
            account.getBalance());
    }
}
