package com.zenhome.assignment.electricityconsumption.entity;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;
import java.util.Optional;

public class Duration {
    private final int duration;
    private final TemporalUnit unit;

    private Duration(int duration, TemporalUnit unit) {
        this.duration = duration;
        this.unit = unit;
    }

    public static Duration of(String durationInput) {
        final boolean isDurationFormat = durationInput.matches("^\\d+[h,H,d,D,m,M]$");
        if (!isDurationFormat) {
            throw new IllegalArgumentException("Duration wrong format, please use regex [^\\d+[[h,H,d,D,m,M]]$] to validate your data");
        }
        int durationTime =
                Optional
                        .of(durationInput)
                        .map(string -> string.substring(0, durationInput.length()-1))
                        .map(Integer::parseInt)
                        .orElseThrow(() -> new IllegalArgumentException("Duration wrong format, please use regex [^\\d+[h,H,m,M,s,S]$] to validate your data"));
        final TemporalUnit unit = getTemporalUnit(getLastCharacter(durationInput));
        return new Duration(durationTime, unit);
    }

    private static String getLastCharacter(String durationInput) {
        return durationInput.substring(durationInput.length() - 1);
    }

    private static ChronoUnit getTemporalUnit(String unit) {
        return switch (unit.toUpperCase()) {
            case "H" -> ChronoUnit.HOURS;
            case "D" -> ChronoUnit.DAYS;
            case "M" -> ChronoUnit.MONTHS;
            default ->
                throw new IllegalArgumentException("Duration wrong format, please use regex [^\\d+[h,H,m,M,s,S]$] to validate your data");
        };

    }

    public LocalDateTime dateTimeBeforeDuration() {
        return LocalDateTime.now().minus(duration, unit);
    }
}
