package com.example.warehouse.repository;

import com.example.warehouse.model.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface LocationRepository extends JpaRepository<Location, Long> {


    public List<Location> findAllByPartsLocation(String partsLocation);
    public List<Location> findAllByPartsLocationAndAmount(String partsLocation, Long Amount);
}
