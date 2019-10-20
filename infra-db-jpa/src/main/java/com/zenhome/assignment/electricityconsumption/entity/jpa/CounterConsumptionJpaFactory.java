package com.zenhome.assignment.electricityconsumption.entity.jpa;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.IntStream;

import static java.time.temporal.ChronoUnit.HOURS;

public class CounterConsumptionJpaFactory {
    private final static int counterSize = 6;
    public static List<CounterConsumptionJpa> createCounterConsumptionDemo() {
        List<CounterConsumptionJpa> list = new ArrayList<>();
        final LocalDateTime today00_hours = LocalDate.now().atTime(0, 0, 0);
        for (int counterId = 0;  counterId < counterSize; counterId++ ) {
            for (int hour = 0;  hour < 24; hour++ ) {
                list.add(getCounterConsumptionJpa(counterId, hour, today00_hours));
            }
        }
        return list;
    }

    private static CounterConsumptionJpa getCounterConsumptionJpa(int counterId, int hour, LocalDateTime today00_hours) {
        final LocalDateTime createdDate =today00_hours.plus(hour, HOURS);
        return new CounterConsumptionJpa(UUID.randomUUID().toString(), String.valueOf(counterId), getRandomAmount(), createdDate);
    }

    private static BigDecimal getRandomAmount() {
        Random randomizer = new Random();
        final double nextDouble = randomizer.nextDouble()*10000000;
        return new BigDecimal(String.valueOf(nextDouble));
    }
}
