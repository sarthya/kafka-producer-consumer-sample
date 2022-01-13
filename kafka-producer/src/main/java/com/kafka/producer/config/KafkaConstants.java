package com.kafka.producer.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@Data
@ConfigurationProperties("kafka.producer")
public class KafkaConstants {
    public String bootStrapServer;
}
