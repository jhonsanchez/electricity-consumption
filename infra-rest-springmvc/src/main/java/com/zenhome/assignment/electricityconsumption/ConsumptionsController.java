package com.zenhome.assignment.electricityconsumption;

import com.zenhome.assignment.electricityconsumption.boundary.AddCounterConsumption;
import com.zenhome.assignment.electricityconsumption.boundary.ListConsumptions;
import com.zenhome.assignment.electricityconsumption.presenter.JsonConsumptionResponseModelListPresenter;
import com.zenhome.assignment.electricityconsumption.requestmodel.AddCounterConsumptionRequest;
import com.zenhome.assignment.electricityconsumption.rest.payloadmodel.AddCounterConsumptionJsonPayload;
import com.zenhome.assignment.electricityconsumption.rest.requestparam.ListConsumptionsRequestParams;
import com.zenhome.assignment.electricityconsumption.rest.viewmodel.VillagesConsumptionReportJson;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriTemplate;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@RestController
@RequestMapping
public class ConsumptionsController {
    static final String RESOURCE_CONSUMPTION_REPORT_URI_TEMPLATE = "/consumption_report";
    static final String RESOURCE_ADD_CONSUMPTION_URI_TEMPLATE = "/counter_callback";

    private final ListConsumptions listConsumptions;
    private final AddCounterConsumption addCounterConsumption;

    public ConsumptionsController(ListConsumptions listConsumptions,
                                  AddCounterConsumption addCounterConsumption) {
        this.listConsumptions = listConsumptions;
        this.addCounterConsumption = addCounterConsumption;
    }

    @GetMapping(RESOURCE_CONSUMPTION_REPORT_URI_TEMPLATE)
    public VillagesConsumptionReportJson consumptions(ListConsumptionsRequestParams params) {
        final JsonConsumptionResponseModelListPresenter presenter = new JsonConsumptionResponseModelListPresenter();
        listConsumptions.execute(params.toRequest(), presenter);
        return presenter.getPresentedResult();
    }

    @PostMapping(RESOURCE_ADD_CONSUMPTION_URI_TEMPLATE)
    public ResponseEntity addCounterConsumption(@RequestBody AddCounterConsumptionJsonPayload payload) {
        addCounterConsumption.execute(AddCounterConsumptionRequest.of(payload.counterId(), payload.amount()));
        return ResponseEntity.created(new UriTemplate(RESOURCE_ADD_CONSUMPTION_URI_TEMPLATE).expand()).build();
    }

    @ExceptionHandler
    void handleIllegalArgumentException(HttpServletResponse response, IllegalArgumentException e) throws IOException {
        response.sendError(HttpStatus.BAD_REQUEST.value());
    }
}
