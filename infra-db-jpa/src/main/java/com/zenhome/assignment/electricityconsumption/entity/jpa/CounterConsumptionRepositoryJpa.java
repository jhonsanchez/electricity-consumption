package com.zenhome.assignment.electricityconsumption.entity.jpa;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface CounterConsumptionRepositoryJpa extends CrudRepository<CounterConsumptionJpa, String> {

    @Query("select  p.counterId, sum(p.amount) from CounterConsumptionJpa p where p.createdDate>=:createdDate group by p.counterId")
    List<CounterConsumptionJpa> findByCreatedDateAfter(@Param("createdDate") LocalDateTime createdDate);

    @Query("select  new com.zenhome.assignment.electricityconsumption.entity.jpa.CounterConsumptionAcumulatorJpa(p.counterId, sum(p.amount)) from CounterConsumptionJpa p where p.createdDate>=:createdDate group by p.counterId")
    List<CounterConsumptionAcumulatorJpa> findAcumulatorByCreatedDateAfter(@Param("createdDate") LocalDateTime createdDate);
}
