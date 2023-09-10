package com.example.warehouse.core;


import com.example.warehouse.service.WorkerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.boot.registry.classloading.spi.ClassLoaderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import com.example.warehouse.service.QueueingApplicationService;

import javax.mail.MessagingException;
import java.io.IOException;

@EnableAsync
@RequiredArgsConstructor
@Configuration
@EnableScheduling
@Slf4j
public class QueueingTaskScheduler {
    @Autowired
    public QueueingApplicationService queueingApplicationservice;

    @Autowired
    private WorkerService workerService;

    @Async
    @Scheduled(cron = "${queueing.cron}")
    public void synchronizeDate() throws MessagingException, IOException {
        log.info("Queueing application started");
//        log.info(workerService.getWorkerListForPart().toString());
//        queueingApplicationservice.sendSimpleMessage("jacqueskawecki@gmail.com", "Welcome Test CronApplication", "Test Message");
        log.info("Queueing application ended");

    }

}
