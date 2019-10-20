package com.zenhome.assignment.electricityconsumption.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Consumption {
    private final String consumptionId;
    private final String counterId;
    private final LocalDateTime creationTime;
    private final Amount amount;
    private Village village;

    private Consumption(String consumptionId, String counterId, LocalDateTime creationTime, Amount amount) {
        this.consumptionId = consumptionId;
        this.counterId = counterId;
        this.creationTime = creationTime;
        this.amount = amount;
    }

    public static Consumption of(String consumptionId, String counterId, LocalDateTime creationTime, BigDecimal amount) {
        return new Consumption(consumptionId, counterId, creationTime, Amount.of(amount));
    }

    public String consumptionId() {
        return consumptionId;
    }
    public BigDecimal amount() {
        return amount.toBigDecimal();
    }

    public void associateVillage(Village village) {
        this.village = village;
    }

    public String counterId() {
        return counterId;
    }
}
