package com.zenhome.assignment.electricityconsumption.rest.viewmodel;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.zenhome.assignment.electricityconsumption.entity.Consumption;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class VillageConsumptionJson {
    @JsonProperty("village_name")
    private final String villageName;
    @JsonProperty("consumption")
    private final String consumption;

    public static VillageConsumptionJson fromDomain(Consumption consumption) {
        return new VillageConsumptionJson(consumption.villageName(), consumption.amountString());
    }
}
