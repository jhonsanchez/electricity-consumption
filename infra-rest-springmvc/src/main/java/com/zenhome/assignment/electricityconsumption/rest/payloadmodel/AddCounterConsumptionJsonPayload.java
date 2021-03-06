package com.zenhome.assignment.electricityconsumption.rest.payloadmodel;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class AddCounterConsumptionJsonPayload {
    @JsonProperty("counter_id")
    private final String counterId;
    @JsonProperty("amount")
    private final String amount;

    @JsonCreator
    public AddCounterConsumptionJsonPayload(
            @JsonProperty("counter_id")
            String counterId,
            @JsonProperty("amount")
            String amount) {
        this.counterId = counterId;
        this.amount = amount;
    }

    public String counterId() {
        return counterId;
    }

    public String amount() {
        return amount;
    }
}
