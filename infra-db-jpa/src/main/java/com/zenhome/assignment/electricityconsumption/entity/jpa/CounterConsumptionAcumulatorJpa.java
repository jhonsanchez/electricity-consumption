package com.zenhome.assignment.electricityconsumption.entity.jpa;

import com.zenhome.assignment.electricityconsumption.entity.Consumption;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import java.math.BigDecimal;

@Setter
@Getter
public class CounterConsumptionAcumulatorJpa {
    private String counterId;
    @Column(precision = 14,scale = 5)
    private BigDecimal amountByCounterId;

    public CounterConsumptionAcumulatorJpa(String counterId, BigDecimal amountObject) {
        this.counterId = counterId;
        this.amountByCounterId = amountObject;
    }

    public Consumption toDomain() {
        return Consumption.of(counterId, amountByCounterId);
    }

}
