package com.zenhome.assignment.electricityconsumption.rest.requestparam;

import com.zenhome.assignment.electricityconsumption.requestmodel.ListConsumptionsRequest;
import lombok.Setter;

@Setter
public class ListConsumptionsRequestParams {
    private String duration;

    public ListConsumptionsRequest toRequest() {
        return ListConsumptionsRequest.of(duration);
    }

}
