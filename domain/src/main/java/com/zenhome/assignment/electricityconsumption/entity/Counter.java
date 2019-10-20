package com.zenhome.assignment.electricityconsumption.entity;

public class Counter {
    private final String counterId;

    private Counter(String counterId) {
        this.counterId = counterId;
    }

    public static Counter of(String counterId) {
        return new Counter(counterId);
    }

    public String counterId() {
        return counterId;
    }
}
