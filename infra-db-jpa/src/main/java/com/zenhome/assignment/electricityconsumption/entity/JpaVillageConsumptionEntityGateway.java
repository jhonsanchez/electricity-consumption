package com.zenhome.assignment.electricityconsumption.entity;

import com.zenhome.assignment.electricityconsumption.entity.jpa.CounterConsumptionJpa;
import com.zenhome.assignment.electricityconsumption.entity.jpa.CounterConsumptionRepositoryJpa;
import com.zenhome.assignment.electricityconsumption.entitygateway.VillageConsumptionEntityGateway;

import javax.inject.Named;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Named
public class JpaVillageConsumptionEntityGateway implements VillageConsumptionEntityGateway {
    private final CounterConsumptionRepositoryJpa counterConsumptionRepositoryJpa;

    public JpaVillageConsumptionEntityGateway(CounterConsumptionRepositoryJpa counterConsumptionRepositoryJpa) {
        this.counterConsumptionRepositoryJpa = counterConsumptionRepositoryJpa;
    }

    @Override
    public List<Consumption> getVillageConsumptionsByDuration(Duration duration) {
        return counterConsumptionRepositoryJpa.findByCreatedDateAfter(duration.dateTimeBeforeDuration())
            .stream()
        .map(CounterConsumptionJpa::toDomain)
        .collect(Collectors.toList())
        ;
    }
    @Override
    public void addVillageConsumption(Counter counter, Consumption consumption) {
        final CounterConsumptionJpa counterConsumptionJpa =
                new CounterConsumptionJpa(consumption.consumptionId(),counter.counterId(), consumption.amount(), LocalDateTime.now());
        counterConsumptionRepositoryJpa.save(counterConsumptionJpa);
    }

    @Override
    public Village getVillageByCounterId(String counterId) {
        return null;
    }
}
