package com.kafka.producer.controller;

import com.kafka.producer.model.KafkaProducerResponse;
import com.kafka.producer.model.ProducerData;
import com.kafka.producer.service.ProducerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;

import java.time.ZonedDateTime;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("kafka")
public class ProducerController {

    private Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @Autowired
    ProducerService producerService;

    @PostMapping("generic/producer")
    public ResponseEntity getResponse(@RequestBody ProducerData request) throws ExecutionException, InterruptedException {
        request.setStartDate(ZonedDateTime.now());
        LOGGER.info("request received : {}", request);
        try {
            producerService.sendMessage(request.getTopic(), request.getData());
            request.setEndDate(ZonedDateTime.now());
            return ResponseEntity.status(HttpStatus.CREATED).body(request);
        } catch (HttpClientErrorException ex) {
            return ResponseEntity.status(ex.getStatusCode()).body(new KafkaProducerResponse(ex.getMessage(), ex.getRawStatusCode()));
        }
    }

}
