package com.example.warehouse.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;

@Entity
public class Transaction {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    private Long workOrderNumber;

    @ManyToOne
    @JoinColumn(name = "part_id")
    @JsonBackReference
    private Part part;

    public Part getPart() {
        return part;
    }

    public void setPart(Part part) {
        this.part = part;
    }

    private Long quantity;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setWorkOrderNumber(Long workOrderNumber) {
        this.workOrderNumber = workOrderNumber;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }



    public Long getWorkOrderNumber() {
        return workOrderNumber;
    }

    public Long getQuantity() {
        return quantity;
    }
}
