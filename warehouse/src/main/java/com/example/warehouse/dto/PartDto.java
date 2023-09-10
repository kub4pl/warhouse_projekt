package com.example.warehouse.dto;

public class PartDto {
    private String partDescription;

    private String typePart;

    private String modelPart;

    private String department;

    private String supplier;

    private String unit;

    private String PESEL;

    public String getPESEL() {
        return PESEL;
    }

    public void setPESEL(String PESEL) {
        this.PESEL = PESEL;
    }

    public void setPartDescription(String partDescription) {
        this.partDescription = partDescription;
    }

    public void setTypePart(String typePart) {
        this.typePart = typePart;
    }

    public void setModelPart(String modelPart) {
        this.modelPart = modelPart;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getPartDescription() {
        return partDescription;
    }

    public String getTypePart() {
        return typePart;
    }

    public String getModelPart() {
        return modelPart;
    }

    public String getDepartment() {
        return department;
    }

    public String getSupplier() {
        return supplier;
    }

    public String getUnit() {
        return unit;
    }
}
