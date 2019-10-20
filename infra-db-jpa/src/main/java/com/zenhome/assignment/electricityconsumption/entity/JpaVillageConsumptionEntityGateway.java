package com.zenhome.assignment.electricityconsumption.entity;

import com.zenhome.assignment.electricityconsumption.entity.jpa.CounterConsumptionAcumulatorJpa;
import com.zenhome.assignment.electricityconsumption.entity.jpa.CounterConsumptionJpa;
import com.zenhome.assignment.electricityconsumption.entity.jpa.CounterConsumptionRepositoryJpa;
import com.zenhome.assignment.electricityconsumption.entitygateway.VillageConsumptionEntityGateway;
import com.zenhome.assignment.electricityconsumption.entitygateway.VillageEntityGateway;

import javax.inject.Named;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Named
public class JpaVillageConsumptionEntityGateway implements VillageConsumptionEntityGateway {
    private final CounterConsumptionRepositoryJpa counterConsumptionRepositoryJpa;
    private final VillageEntityGateway villageEntityGateway;

    public JpaVillageConsumptionEntityGateway(CounterConsumptionRepositoryJpa counterConsumptionRepositoryJpa,
                                              VillageEntityGateway villageEntityGateway) {
        this.counterConsumptionRepositoryJpa = counterConsumptionRepositoryJpa;
        this.villageEntityGateway = villageEntityGateway;
    }

    @Override
    public List<Consumption> getVillageConsumptionsByDuration(Duration duration) {
        final List<Consumption> consumptions = counterConsumptionRepositoryJpa.findAcumulatorByCreatedDateAfter(duration.dateTimeBeforeDuration())
                .stream()
                .map(CounterConsumptionAcumulatorJpa::toDomain)
                .collect(Collectors.toList());
        consumptions
                .forEach(this::associateVillageConsumer);
        return consumptions;
    }

    private void associateVillageConsumer(Consumption consumption) {
        final Village village = villageEntityGateway.getVillageByCounterId(consumption.counterId());
        consumption.associateVillage(village);
    }

    @Override
    public void addVillageConsumption(Counter counter, Consumption consumption) {
        final CounterConsumptionJpa counterConsumptionJpa =
                CounterConsumptionJpa.of(counter.counterId(), consumption.amount());
        counterConsumptionRepositoryJpa.save(counterConsumptionJpa);
    }
}
