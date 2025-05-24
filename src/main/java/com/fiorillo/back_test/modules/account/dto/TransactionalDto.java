package com.fiorillo.back_test.modules.account.dto;

import com.fiorillo.back_test.modules.account.enums.EPayment;

import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;

public record TransactionalDto(@NotNull Integer numberAccount, @NotNull BigDecimal value, @NotNull EPayment paymentMethod) {
}
