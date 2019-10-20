package com.zenhome.assignment.electricityconsumption.entity.external;

import com.zenhome.assignment.electricityconsumption.entity.Village;
import com.zenhome.assignment.electricityconsumption.entity.propery.InfraDbProperties;
import com.zenhome.assignment.electricityconsumption.entitygateway.VillageEntityGateway;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.client.RestTemplate;

import javax.inject.Named;
import java.util.Arrays;
import java.util.List;

@Named
@Slf4j
public class ExternalApiVillageEntityGateway implements VillageEntityGateway {

    private static final String VILLAGES_FACTORY = "Villarriba,Frankfurt,Berlin,Lima,Toronto,Ohio";
    private final RestTemplate restTemplate;
    private final InfraDbProperties infraDbProperties;

    public ExternalApiVillageEntityGateway(RestTemplate restTemplate, InfraDbProperties infraDbProperties) {
        this.restTemplate = restTemplate;
        this.infraDbProperties = infraDbProperties;
    }

    @Override
    public Village getVillageByCounterId(String counterId) {
        try {
            return getVillageFromApi(counterId);
        } catch (Exception e) {
            log.error("Error with external API URL: {}counter?id={}", infraDbProperties.getVillageExternalApiUrl() + "counter?id=", counterId);
            return getVillageFromMock(counterId);
        }
    }

    private Village getVillageFromApi(String counterId) {
        final ExternalVillageJson villageJson =
                restTemplate.getForObject(infraDbProperties.getVillageExternalApiUrl() + "counter?id=", ExternalVillageJson.class, counterId);
        return Village.of(villageJson.getVillageName());
    }

    private Village getVillageFromMock(String counterId) {
        final List<String> villages = Arrays.asList(VILLAGES_FACTORY.split(","));
        try {
        final String villageName = villages.get(Integer.parseInt(counterId));
            return Village.of(villageName);
        } catch (Exception e) {
            return Village.fromCounterId(counterId);
        }
    }
}
