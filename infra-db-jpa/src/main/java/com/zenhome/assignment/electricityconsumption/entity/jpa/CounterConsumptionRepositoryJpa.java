package com.zenhome.assignment.electricityconsumption.entity.jpa;

import org.springframework.data.repository.CrudRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface CounterConsumptionRepositoryJpa extends CrudRepository<CounterConsumptionJpa,String> {
    List<CounterConsumptionJpa> findByCreatedDateAfter(LocalDateTime createdDate);
}
