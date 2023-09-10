package com.example.warehouse.repository.repositoryInterface;

import com.example.warehouse.model.Transaction;
import com.example.warehouse.model.Part;
import java.util.List;

public interface PartDescriptionOneToMany {

    public String getPartDescription();
    public String getModelPart();
    public String getSupplier();
    public Long getWorkOrderNumber();
    public Long getQuantity();

//    public Long getQuantity();
//    public Long getWorkOrderNumber();

}
