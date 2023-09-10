package com.example.warehouse.dto;

public class LocationDto {

    private String partsLocation;

    private Long amount;

    public void setPartsLocation(String partsLocation) {
        this.partsLocation = partsLocation;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public String getPartsLocation() {
        return partsLocation;
    }

    public Long getAmount() {
        return amount;
    }
}
