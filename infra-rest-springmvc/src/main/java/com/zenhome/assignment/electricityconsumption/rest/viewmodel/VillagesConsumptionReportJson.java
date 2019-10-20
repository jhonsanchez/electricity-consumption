package com.zenhome.assignment.electricityconsumption.rest.viewmodel;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
@Getter
public class VillagesConsumptionReportJson {
    @JsonProperty("villages")
    private final List<VillageConsumptionJson> villagesConsumption;

}
