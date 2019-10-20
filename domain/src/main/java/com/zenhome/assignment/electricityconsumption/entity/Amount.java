package com.zenhome.assignment.electricityconsumption.entity;

import java.math.BigDecimal;

public class Amount {
    private final BigDecimal amount;
    private final String unit = "Watthour";

    private Amount(BigDecimal amount) {
        this.amount = amount;
    }

    public static Amount of(String amount) {
        return new Amount(new BigDecimal(amount));
    }
    public static Amount of(BigDecimal amount) {
        return new Amount(amount);
    }
    public BigDecimal toBigDecimal() {
        return amount;
    }
    public String toString() {
        return String.valueOf(amount);
    }
}
