package com.fiorillo.back_test.modules.account.dto;

import com.fiorillo.back_test.modules.account.model.Account;

import java.math.BigDecimal;

public record AccountResponse(Integer id, Integer numberAccount, BigDecimal balence) {

    public static AccountResponse of(Account account) {
        return new AccountResponse(
            account.getId(),
            account.getNumberAccount(),
            account.getBalance());
    }
}
