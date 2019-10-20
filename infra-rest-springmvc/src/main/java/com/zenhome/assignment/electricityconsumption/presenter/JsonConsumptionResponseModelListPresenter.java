package com.zenhome.assignment.electricityconsumption.presenter;

import com.zenhome.assignment.electricityconsumption.responsemodel.ConsumptionsResponse;
import com.zenhome.assignment.electricityconsumption.rest.viewmodel.VillagesConsumptionReportJson;

import java.util.function.Consumer;

public class JsonConsumptionResponseModelListPresenter implements Consumer<ConsumptionsResponse> {
    private VillagesConsumptionReportJson villagesConsumptionReport;
    @Override
    public void accept(ConsumptionsResponse consumptionsResponse) {
    }

    public VillagesConsumptionReportJson getPresentedResult() {
        return villagesConsumptionReport;
    }
}
