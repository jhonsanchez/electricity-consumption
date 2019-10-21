package com.zenhome.assignment.electricityconsumption.interactor;

import com.zenhome.assignment.electricityconsumption.entity.Consumption;
import com.zenhome.assignment.electricityconsumption.entity.Counter;
import com.zenhome.assignment.electricityconsumption.entity.Duration;
import com.zenhome.assignment.electricityconsumption.entitygateway.VillageConsumptionEntityGateway;
import com.zenhome.assignment.electricityconsumption.requestmodel.AddCounterConsumptionRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

class AddCounterConsumptionImplTest {

    private VillageConsumptionEntityGateway villageConsumptionEntityGateway;

    @BeforeEach
    void initializeComponentUnderTest() {
        villageConsumptionEntityGateway = mock(VillageConsumptionEntityGateway.class);
    }
    @Test
    void shouldAddCounterConsumption() {
        //given
        final String counterId = "1";
        final String amount = "222.123";
        AddCounterConsumptionRequest request = AddCounterConsumptionRequest.of(counterId, amount);
        final Counter counter = request.counter();
        final BigDecimal amountBigDecimal = new BigDecimal(amount);
        final List<Consumption> consumptions = Collections.singletonList(Consumption.of(counterId, amountBigDecimal));
        doReturn(consumptions).when(villageConsumptionEntityGateway).getVillageConsumptionsByDuration(any());
        //when
        villageConsumptionEntityGateway.addVillageConsumption(counter, Consumption.of(counter.counterId(), amountBigDecimal));
        //then
        final List<Consumption> villageConsumptionsByDuration = villageConsumptionEntityGateway.getVillageConsumptionsByDuration(Duration.of("24h"));
        assertEquals(1,villageConsumptionsByDuration.size());
    }
}