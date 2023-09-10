package com.example.warehouse.service;
import com.example.warehouse.dto.WorkerDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WorkerService {

        private final WorkerFeignClient workerFeignClient;

        @Autowired
        public WorkerService(WorkerFeignClient workerFeignClient) {
            this.workerFeignClient = workerFeignClient;
        }

        public WorkerDto getWorkerListForPart() {
            return workerFeignClient.getWorkerPage(5);
        }


        public WorkerDto getWorkerPart() {
        return workerFeignClient.getWorkerPart("98080765240");
    }
}
