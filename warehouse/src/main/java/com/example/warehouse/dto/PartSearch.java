package com.example.warehouse.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PartSearch {

    private String partDescription;
    private String supplier;
    private String modelPart;
    private String department;
}
