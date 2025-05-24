package com.fiorillo.back_test.modules.account.enums;

import lombok.Getter;

@Getter
public enum EPayment {

    P("Pix", 0),
    C("Cartão de crédito", 5),
    D("Cartão de débito", 4);

    private final String description;
    private final Integer rate;

    EPayment(String description, Integer rate) {
        this.description = description;
        this.rate = rate;
    }
}
