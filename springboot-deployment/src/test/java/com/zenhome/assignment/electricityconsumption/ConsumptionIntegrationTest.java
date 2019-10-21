package com.zenhome.assignment.electricityconsumption;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zenhome.assignment.electricityconsumption.entity.Consumption;
import com.zenhome.assignment.electricityconsumption.entity.Counter;
import com.zenhome.assignment.electricityconsumption.entity.jpa.CounterConsumptionRepositoryJpa;
import com.zenhome.assignment.electricityconsumption.entitygateway.VillageConsumptionEntityGateway;
import com.zenhome.assignment.electricityconsumption.rest.payloadmodel.AddCounterConsumptionJsonPayload;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
class ConsumptionIntegrationTest {
    @Autowired
    private VillageConsumptionEntityGateway villageConsumptionEntityGateway;
    @Autowired
    private CounterConsumptionRepositoryJpa counterConsumptionRepositoryJpa;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private MockMvc mockMvc;

    @Test
    void contextLoads() {

    }
    @Test
    void testConsumptionReportWithNoConsumptions() throws Exception {
        //given
        counterConsumptionRepositoryJpa.deleteAll();
        // expect

        mockMvc
                .perform(get("/consumption_report?duration=24h"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("villages").isEmpty());
    }
    @Test
    void testConsumptionReportWithConsumptions() throws Exception {
        final String counterId = "1";
        final BigDecimal amount = new BigDecimal("222.123");
        villageConsumptionEntityGateway.addVillageConsumption(Counter.of(counterId), Consumption.of(counterId, amount));
        mockMvc
                .perform(get("/consumption_report?duration=24h"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("villages").isNotEmpty());

    }
    @Test
    void testConsumptionBadRequest() throws Exception {
        final String counterId = "1";
        final BigDecimal amount = new BigDecimal("222.123");
        villageConsumptionEntityGateway.addVillageConsumption(Counter.of(counterId), Consumption.of(counterId, amount));
        mockMvc
                .perform(get("/consumption_report?duration=24L"))
                .andExpect(status().isBadRequest());
    }
    @Test
    void testAddCounterConsumptionOk() throws Exception {
        //given
        counterConsumptionRepositoryJpa.deleteAll();
        final String counterId = "1";
        final String amount = "10000.123";
        final AddCounterConsumptionJsonPayload addCounterConsumptionJsonPayload = new AddCounterConsumptionJsonPayload(counterId, amount);
        //when
        mockMvc
                .perform(post("/counter_callback",addCounterConsumptionJsonPayload)
                        .content(objectMapper.writeValueAsString(addCounterConsumptionJsonPayload))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }
    @Test
    void testAddCounterConsumptionBadAmount() throws Exception {
        //given
        counterConsumptionRepositoryJpa.deleteAll();
        final String counterId = "1";
        final String amount = "10000.123asd";
        final AddCounterConsumptionJsonPayload addCounterConsumptionJsonPayload = new AddCounterConsumptionJsonPayload(counterId, amount);
        //when
        mockMvc
                .perform(post("/counter_callback",addCounterConsumptionJsonPayload)
                        .content(objectMapper.writeValueAsString(addCounterConsumptionJsonPayload))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

}
