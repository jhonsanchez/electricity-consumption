package com.zenhome.assignment.electricityconsumption;

import com.zenhome.assignment.electricityconsumption.boundary.ListConsumptions;
import com.zenhome.assignment.electricityconsumption.presenter.JsonConsumptionResponseModelListPresenter;
import com.zenhome.assignment.electricityconsumption.rest.requestparam.ListConsumptionsRequestParams;
import com.zenhome.assignment.electricityconsumption.rest.viewmodel.VillagesConsumptionReportJson;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.zenhome.assignment.electricityconsumption.ConsumptionsController.RESOURCE_URI_TEMPLATE;

@RestController
@RequestMapping(RESOURCE_URI_TEMPLATE)
public class ConsumptionsController {
    static final String RESOURCE_URI_TEMPLATE = "/consumption_report";

    private final ListConsumptions listConsumptions;

    public ConsumptionsController(ListConsumptions listConsumptions) {
        this.listConsumptions = listConsumptions;
    }

    @GetMapping
    public VillagesConsumptionReportJson consumptions(ListConsumptionsRequestParams params) {
        final JsonConsumptionResponseModelListPresenter presenter = new JsonConsumptionResponseModelListPresenter();
        listConsumptions.execute(params.toRequest(), presenter);
        return presenter.getPresentedResult();
    }
}
