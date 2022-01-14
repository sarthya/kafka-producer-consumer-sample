package com.kafka.producer.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class KafkaProducerResponse {
    private String message;
    private int code;
}
