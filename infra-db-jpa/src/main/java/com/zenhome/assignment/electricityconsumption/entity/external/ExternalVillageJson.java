package com.zenhome.assignment.electricityconsumption.entity.external;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@Getter
@Setter
public class ExternalVillageJson {
    @JsonProperty("id")
    private final String id;
    @JsonProperty("village_name")
    private final String villageName;
}
