package com.zenhome.assignment.electricityconsumption.presenter;

import com.zenhome.assignment.electricityconsumption.responsemodel.ConsumptionsResponse;
import com.zenhome.assignment.electricityconsumption.rest.viewmodel.VillageConsumptionJson;
import com.zenhome.assignment.electricityconsumption.rest.viewmodel.VillagesConsumptionReportJson;

import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class JsonConsumptionResponseModelListPresenter implements Consumer<ConsumptionsResponse> {
    private VillagesConsumptionReportJson villagesConsumptionReport;

    @Override
    public void accept(ConsumptionsResponse consumptionsResponse) {
        final List<VillageConsumptionJson> villageConsumptionJson = consumptionsResponse
                .consumptions()
                .stream()
                .map(VillageConsumptionJson::fromDomain)
                .collect(Collectors.toList());
        villagesConsumptionReport = new VillagesConsumptionReportJson(villageConsumptionJson);
    }

    public VillagesConsumptionReportJson getPresentedResult() {
        return villagesConsumptionReport;
    }
}
