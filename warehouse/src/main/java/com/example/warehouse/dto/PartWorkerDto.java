package com.example.warehouse.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class PartWorkerDto {

    String partDescription;
    String modelPart;
    String supplier;

    String name;
    String surname;

}
