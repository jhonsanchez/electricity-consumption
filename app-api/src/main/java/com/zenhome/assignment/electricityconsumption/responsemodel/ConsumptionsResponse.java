package com.zenhome.assignment.electricityconsumption.responsemodel;

import com.zenhome.assignment.electricityconsumption.entity.Consumption;

import java.util.List;

public class ConsumptionsResponse {
    private final List<Consumption> consumptions;

    public ConsumptionsResponse(List<Consumption> consumptions) {
        this.consumptions = consumptions;
    }
}
