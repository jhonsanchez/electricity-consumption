package com.zenhome.assignment.electricityconsumption.boundary;

import com.zenhome.assignment.electricityconsumption.requestmodel.ListConsumptionsRequest;
import com.zenhome.assignment.electricityconsumption.responsemodel.ConsumptionsResponse;

import java.util.function.Consumer;

@FunctionalInterface
@Boundary
public interface ListConsumptions {
    void execute(ListConsumptionsRequest listConsumptionsRequest, Consumer<ConsumptionsResponse> responseModelFunction);
}
