package com.kafka.producer.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpClientErrorException;

public class KafkaConfigurationException extends HttpClientErrorException {
    public KafkaConfigurationException(String message, HttpStatus httpStatus) {
        super(httpStatus, message);
    }
}
