package com.zenhome.assignment.electricityconsumption.entity;

public class Village {
    private String villageId;
    private final String villaneName;

    private Village(String villageId, String villaneName) {
        this.villageId = villageId;
        this.villaneName = villaneName;
    }

    private Village(String villaneName) {
        this.villaneName = villaneName;
    }

    public static Village of(String villaneName) {
        return new Village(villaneName);
    }
    public static Village fromCounterId(String counterId) {
        return new Village("Village id["+counterId+"]");
    }

    public String villageName() {
        return villaneName;
    }
}
