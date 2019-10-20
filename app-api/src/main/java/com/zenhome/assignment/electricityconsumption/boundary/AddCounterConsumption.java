package com.zenhome.assignment.electricityconsumption.boundary;

import com.zenhome.assignment.electricityconsumption.requestmodel.AddCounterConsumptionRequest;
import com.zenhome.assignment.electricityconsumption.requestmodel.ListConsumptionsRequest;

@FunctionalInterface
@Boundary
public interface AddCounterConsumption {
    void execute(AddCounterConsumptionRequest addCounterConsumptionsRequest);
}
