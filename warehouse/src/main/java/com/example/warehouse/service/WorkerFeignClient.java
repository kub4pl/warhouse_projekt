package com.example.warehouse.service;

import com.example.warehouse.dto.WorkerDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "worker-service", url = "http://localhost:8080") // Zmodyfikuj URL do właściwego API
public interface WorkerFeignClient {


        @GetMapping("/api/worker/{id}")
        WorkerDto getWorkerPage( @PathVariable long id);

        @GetMapping("/api/worker/pesel/{PESEL}")
        WorkerDto getWorkerPart( @PathVariable String PESEL);



    }
