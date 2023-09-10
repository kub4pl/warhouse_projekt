package com.example.warehouse.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;

@Entity
public class Location {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    @Column(columnDefinition = "varchar(1023)")
    private String partsLocation;

    @ManyToOne
    @JoinColumn(name = "part_id")
    @JsonBackReference
    private Part part;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Part getPart() {
        return part;
    }

    public void setPart(Part part) {
        this.part = part;
    }

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

    // tu widoczne też minimum i maximum
}
