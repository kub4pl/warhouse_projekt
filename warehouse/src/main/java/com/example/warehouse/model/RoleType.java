package com.example.warehouse.model;

public enum RoleType {

    ADMINISTRATOR("ADMINISTRATOR"),

    USER("USER");

    private String value;

    RoleType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
