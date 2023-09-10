package com.example.warehouse.repository;

import com.example.warehouse.model.Stock;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StockRepository extends JpaRepository<Stock, Long> {


    public List<Stock> findAllByNumberWarehouse(Long numberWarehouse);

}
