package com.zenhome.assignment.electricityconsumption.entity.external;

import com.zenhome.assignment.electricityconsumption.entity.Village;
import com.zenhome.assignment.electricityconsumption.entitygateway.VillageEntityGateway;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.client.RestTemplate;

import javax.inject.Named;
import java.util.Arrays;
import java.util.List;

@Named
@Slf4j
public class ExternalApiVillageEntityGateway implements VillageEntityGateway {
    private static final String EXTERNAL_VILLAGE_API_URL = "http://localhost:8081/";
    private static final String VILLAGES_FACTORY = "Villarriba,Frankfurt,Berlin,Lima,Toronto,Ohio";
    private final RestTemplate restTemplate;

    public ExternalApiVillageEntityGateway(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public Village getVillageByCounterId(String counterId) {
        try {
            return getVillageFromApi(counterId);
        } catch (Exception e) {
            log.error("Error with external API URL: {}counter?id={}", EXTERNAL_VILLAGE_API_URL, counterId);
            return getVillageFromMock(counterId);
        }
    }

    private Village getVillageFromApi(String counterId) {
        final ExternalVillageJson villageJson =
                restTemplate.getForObject(EXTERNAL_VILLAGE_API_URL + "counter?id=", ExternalVillageJson.class, counterId);
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
