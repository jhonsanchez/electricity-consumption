package com.zenhome.assignment.electricityconsumption.entity.jpa;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ConsumptionJpa {
    @Id
    private String consumptionId;
    private String counterId;

}
