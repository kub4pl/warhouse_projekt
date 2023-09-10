package com.example.warehouse.repository;

import com.example.warehouse.model.Transaction;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {


    public Optional<Transaction> findById(Long id);
    public List<Transaction> findAllByIdAndWorkOrderNumber(Long id, Long workOrderNumber);
}

