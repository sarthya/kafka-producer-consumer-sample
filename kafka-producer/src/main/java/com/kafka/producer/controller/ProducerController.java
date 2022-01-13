package com.kafka.producer.controller;

import com.kafka.producer.model.ProducerData;
import com.kafka.producer.service.ProducerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.time.ZonedDateTime;

@RestController
@RequestMapping("kafka")
public class ProducerController {

    private Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @Autowired
    ProducerService producerService;

    @PostMapping("generic/producer")
    public ResponseEntity getResponse(@RequestBody ProducerData request) {
        request.setStartDate(ZonedDateTime.now());
        LOGGER.info("request received : {}", request);
        producerService.sendMessage(request.getTopic(), request.getData());
        request.setEndDate(ZonedDateTime.now());
        return ResponseEntity.created(URI.create("")).body(request);
    }

}
