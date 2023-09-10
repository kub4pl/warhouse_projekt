package com.example.warehouse.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
@Entity
public class Stock {


    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long numberWarehouse;

    private Long min_value;

    private Long max_value;

    public void setNumberWarehouse(Long numberWarehouse) {
        this.numberWarehouse = numberWarehouse;
    }

    public void setMin_value(Long min_value) {
        this.min_value = min_value;
    }

    public void setMax_value(Long max_value) {
        this.max_value = max_value;
    }

    public Long getNumberWarehouse() {
        return numberWarehouse;
    }

    public Long getMin_value() {
        return min_value;
    }

    public Long getMax_value() {
        return max_value;
    }
}
