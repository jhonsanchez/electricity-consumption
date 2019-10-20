package com.zenhome.assignment.electricityconsumption.entitygateway;

import com.zenhome.assignment.electricityconsumption.entity.Consumption;
import com.zenhome.assignment.electricityconsumption.entity.Counter;
import com.zenhome.assignment.electricityconsumption.entity.Duration;

import java.util.List;

public interface VillageConsumptionEntityGateway {
    List<Consumption> getVillageConsumptionsByDuration(Duration duration);

    void addVillageConsumption(Counter counter, Consumption consumption);
}
