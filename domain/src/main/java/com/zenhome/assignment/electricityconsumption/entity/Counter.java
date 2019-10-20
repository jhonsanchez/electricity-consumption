package com.zenhome.assignment.electricityconsumption.entity;

public class Counter {
    private final String counterId;
    private final Village village;

    public Counter(String counterId, Village village) {
        this.counterId = counterId;
        this.village = village;
    }

    public String counterId() {
        return counterId;
    }
}
