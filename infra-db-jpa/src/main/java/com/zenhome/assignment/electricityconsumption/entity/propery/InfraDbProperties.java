package com.zenhome.assignment.electricityconsumption.entity.propery;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "external")
@Getter
@Setter
public class InfraDbProperties {
    private String villageExternalApiUrl = "http://localhost:8081/";
}
