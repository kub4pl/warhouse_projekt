package com.example.warehouse.controller;

import com.example.warehouse.dto.LocationDto;
import com.example.warehouse.dto.StockDto;
import com.example.warehouse.model.Location;
import com.example.warehouse.model.Stock;
import com.example.warehouse.repository.LocationRepository;
import com.example.warehouse.repository.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class StockController {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)

    @Autowired
    private StockRepository stockRepository;

    @PostMapping("/stock")
    public StockDto createStock(@RequestBody StockDto stockDto) {
        Stock stock = new Stock();
        stock.setNumberWarehouse(stockDto.getNumberWarehouse());
        stock.setMin_value(stockDto.getMin_value());
        stock.setMax_value(stockDto.getMax_value());
        stockRepository.save(stock);
        return stockDto;
    }
    @PutMapping("stock/{id}")
    public StockDto editStock (@RequestBody StockDto stockDto, @PathVariable Long id){
        Stock stock = stockRepository.getReferenceById(id);
        stock.setNumberWarehouse(stockDto.getNumberWarehouse());
        stock.setMin_value(stockDto.getMin_value());
        stock.setMax_value(stockDto.getMax_value());
        stockRepository.save(stock);
        return stockDto;
    }
//    @GetMapping("/stock/{id}")
//    public StockDto getStock(@PathVariable Long id) {
//        StockDto stockDto = new StockDto();
//        Stock stock = stockRepository.getReferenceById(id);
//        locationDto.setPartsLocation(location.getPartsLocation());
//        locationDto.setAmount(location.getAmount());
//        return locationDto;
//    }
//    @DeleteMapping("/location/{id}")
//    public Boolean deleteLocation(@PathVariable Long id) {
//
//        locationRepository.deleteById(id);
//        return true;
//    }
}
