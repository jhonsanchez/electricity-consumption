package com.zenhome.assignment.electricityconsumption.property;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "runner")
@Getter
@Setter
public class RunnerProperties {
    private boolean populateData = false;
}
