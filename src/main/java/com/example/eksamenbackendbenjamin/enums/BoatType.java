package com.example.eksamenbackendbenjamin.enums;

public enum BoatType {
    FEMOGTYVEFOD("25fod"),
    FEMOGTYVETILFYRREFOD("25-40fod"),
    FYRREFOD("40fod");

    private final String description;


    BoatType(String description) {
        this.description = description;

    }

    public String getDescription() {
        return description;
    }
}
