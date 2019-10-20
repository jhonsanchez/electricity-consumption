package com.zenhome.assignment.electricityconsumption.interactor;

import com.zenhome.assignment.electricityconsumption.boundary.AddCounterConsumption;
import com.zenhome.assignment.electricityconsumption.entity.Amount;
import com.zenhome.assignment.electricityconsumption.entity.Consumption;
import com.zenhome.assignment.electricityconsumption.entity.Counter;
import com.zenhome.assignment.electricityconsumption.entitygateway.VillageConsumptionEntityGateway;
import com.zenhome.assignment.electricityconsumption.requestmodel.AddCounterConsumptionRequest;

import javax.inject.Named;

@Named
public class AddCounterConsumptionImpl implements AddCounterConsumption {
    private final VillageConsumptionEntityGateway villageConsumptionEntityGateway;

    public AddCounterConsumptionImpl(VillageConsumptionEntityGateway villageConsumptionEntityGateway) {
        this.villageConsumptionEntityGateway = villageConsumptionEntityGateway;
    }

    @Override
    public void execute(AddCounterConsumptionRequest addCounterConsumptionsRequest) {
        final Counter counter = addCounterConsumptionsRequest.counter();
        final Amount amount = addCounterConsumptionsRequest.amount();
        villageConsumptionEntityGateway.addVillageConsumption(counter,
                Consumption.of(counter.counterId(),amount.toBigDecimal()));
    }
}
