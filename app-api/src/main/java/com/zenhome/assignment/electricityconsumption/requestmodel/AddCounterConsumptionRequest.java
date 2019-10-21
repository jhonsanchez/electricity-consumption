package com.zenhome.assignment.electricityconsumption.requestmodel;

import com.zenhome.assignment.electricityconsumption.entity.Amount;
import com.zenhome.assignment.electricityconsumption.entity.Counter;

import javax.annotation.Nonnull;

public class AddCounterConsumptionRequest {
    private final Counter counter;
    private final Amount amount;


    private AddCounterConsumptionRequest(Counter counter, Amount amount) {
        this.counter = counter;
        this.amount = amount;
    }

    public static AddCounterConsumptionRequest of(@Nonnull String counterId, @Nonnull String amount) {
        return new AddCounterConsumptionRequest(Counter.of(counterId), Amount.of(amount));
    }

    public Counter counter() {
        return counter;
    }

    public Amount amount() {
        return amount;
    }
}
