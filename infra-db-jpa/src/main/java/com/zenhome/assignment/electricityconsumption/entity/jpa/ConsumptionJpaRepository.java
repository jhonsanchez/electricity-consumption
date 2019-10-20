package com.zenhome.assignment.electricityconsumption.entity.jpa;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConsumptionJpaRepository extends CrudRepository<ConsumptionJpa,String> {
}