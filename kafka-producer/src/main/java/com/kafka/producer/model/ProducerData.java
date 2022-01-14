package com.kafka.producer.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.ZonedDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ProducerData {

    private Long id;
    private String message;
    private String topic;
    private ZonedDateTime startDate;
    private ZonedDateTime endDate;
    private Object data;
}
