package com.example.warehouse.controller;

import com.example.warehouse.dto.LocationDto;
import com.example.warehouse.dto.TransactionDto;
import com.example.warehouse.model.Location;
import com.example.warehouse.model.Transaction;
import com.example.warehouse.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class TransactionController {

    @Autowired
    private TransactionRepository transactionRepository;

    @PostMapping("/transaction")
    public TransactionDto createTransaction(@RequestBody TransactionDto transactionDto) {
        Transaction transaction = new Transaction();
        transaction.setWorkOrderNumber(transactionDto.getWorkOrderNumber());
        transaction.setQuantity(transactionDto.getQuantity());
        transactionRepository.save(transaction);
        return transactionDto;
    }
    @PutMapping("transaction/{id}")
    public TransactionDto editTransaction (@RequestBody TransactionDto transactionDto, @PathVariable Long id) {
        Transaction transaction = transactionRepository.getReferenceById(id);
        transaction.setWorkOrderNumber(transactionDto.getWorkOrderNumber());
        transaction.setQuantity(transactionDto.getQuantity());
        transactionRepository.save(transaction);
        return transactionDto;
    }
    @GetMapping("/transaction/{id}")
    public TransactionDto getTransaction(@PathVariable Long id) {
        TransactionDto transactionDto = new TransactionDto();
        Transaction transaction = transactionRepository.getReferenceById(id);
        transactionDto.setWorkOrderNumber(transaction.getWorkOrderNumber());
        transactionDto.setQuantity(transaction.getQuantity());
        return transactionDto;
    }
    @DeleteMapping("/transaction/{id}")
    public Boolean deleteTransaction(@PathVariable Long id) {

        transactionRepository.deleteById(id);
        return true;
    }

}
