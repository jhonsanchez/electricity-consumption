package com.zenhome.assignment.electricityconsumption.entity;

import com.zenhome.assignment.electricityconsumption.entitygateway.VillageEntityGateway;

import java.util.List;

public class VillageConsumption {
    private final List<Consumption> consumption;

    private VillageConsumption(List<Consumption> consumption) {
        this.consumption = consumption;
    }

    public static VillageConsumption of(List<Consumption> consumption, VillageEntityGateway villageConsumptionEntityGateway) {
        consumption
                .forEach(p -> p.associateVillage(villageConsumptionEntityGateway.getVillageByCounterId(p.counterId())));
        return new VillageConsumption(consumption);
    }
}
