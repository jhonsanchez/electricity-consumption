package com.zenhome.assignment.electricityconsumption.entity.jpa;

import com.zenhome.assignment.electricityconsumption.entity.Consumption;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
public class CounterConsumptionJpa {
    @Id
    private String consumptionId;
    private String counterId;
    @Column(precision = 14,scale = 5)
    private BigDecimal amount;
    private LocalDateTime createdDate;

    public CounterConsumptionJpa() {
    }

    public CounterConsumptionJpa(String consumptionId, String counterId, BigDecimal amount, LocalDateTime createdDate) {
        this.consumptionId = consumptionId;
        this.counterId = counterId;
        this.amount = amount;
        this.createdDate = createdDate;
    }

    public static CounterConsumptionJpa of(String counterId, BigDecimal amount) {
        return new CounterConsumptionJpa(UUID.randomUUID().toString(),
                counterId,
                amount,
                LocalDateTime.now()
        );
    }

    public String counterId() {
        return counterId;
    }

    public BigDecimal amount() {
        return amount;
    }

    public LocalDateTime createdDate() {
        return createdDate;
    }

    public Consumption toDomain() {
        return Consumption.of(consumptionId, counterId, createdDate, amount);
    }
}
