package com.example.warehouse.model;
import javax.persistence.*;
import java.util.List;


@Entity
public class Part {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "varchar(1023)")
    @OneToMany(mappedBy = "part")
    private List<Transaction> transactions;


    @Lob
    private byte[] data;

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }

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

    public void setId(Long id) {
        this.id = id;
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

    public Long getId() {
        return id;
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
