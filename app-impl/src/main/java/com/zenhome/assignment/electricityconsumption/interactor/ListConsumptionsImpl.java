package com.zenhome.assignment.electricityconsumption.interactor;

import com.zenhome.assignment.electricityconsumption.boundary.ListConsumptions;
import com.zenhome.assignment.electricityconsumption.entity.Consumption;
import com.zenhome.assignment.electricityconsumption.entitygateway.VillageConsumptionEntityGateway;
import com.zenhome.assignment.electricityconsumption.requestmodel.ListConsumptionsRequest;
import com.zenhome.assignment.electricityconsumption.responsemodel.ConsumptionsResponse;

import javax.inject.Named;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

@Named
public class ListConsumptionsImpl implements ListConsumptions {
    private final VillageConsumptionEntityGateway villageConsumptionEntityGateway;

    public ListConsumptionsImpl(VillageConsumptionEntityGateway villageConsumptionEntityGateway) {
        this.villageConsumptionEntityGateway = villageConsumptionEntityGateway;
    }

    @Override
    public void execute(ListConsumptionsRequest listConsumptionsRequest, Consumer<ConsumptionsResponse> responseModelFunction) {
        final List<Consumption> consumptions = getConsumptions(listConsumptionsRequest);
        responseModelFunction.accept(new ConsumptionsResponse(consumptions));
    }

    private List<Consumption> getConsumptions(ListConsumptionsRequest listConsumptionsRequest) {
        return Optional.of(listConsumptionsRequest)
                .map(ListConsumptionsRequest::duration)
                .map(villageConsumptionEntityGateway::getVillageConsumptionsByDuration)
                .orElse(Collections.emptyList());
    }
}