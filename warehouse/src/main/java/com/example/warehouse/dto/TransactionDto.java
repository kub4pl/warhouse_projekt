package com.example.warehouse.dto;

public class TransactionDto {




    private Long workOrderNumber;

    private Long quantity;



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
