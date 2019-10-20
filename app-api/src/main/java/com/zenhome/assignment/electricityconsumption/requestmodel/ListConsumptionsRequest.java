package com.zenhome.assignment.electricityconsumption.requestmodel;

import com.zenhome.assignment.electricityconsumption.entity.Duration;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class ListConsumptionsRequest {
    private final Duration duration;

    private ListConsumptionsRequest(@Nonnull Duration duration) {
        //TODO
        //Validate time format
        this.duration = duration;
    }

    public static ListConsumptionsRequest of(@Nullable String duration) {
        if(duration==null)
            throw new IllegalArgumentException("Duration cannot be null...");
        return new ListConsumptionsRequest(Duration.of(duration));
    }

    public Duration duration() {
        return duration;
    }
}
