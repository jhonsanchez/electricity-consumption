package com.zenhome.assignment.electricityconsumption.entitygateway;

import com.zenhome.assignment.electricityconsumption.entity.Village;

public interface VillageEntityGateway {
    Village getVillageByCounterId(String counterId);

}
