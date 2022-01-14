package com.kafka.producer.service;

import com.kafka.producer.config.KafkaProducerConfigProperties;
import com.kafka.producer.exception.KafkaConfigurationException;
import org.apache.kafka.clients.admin.AdminClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import java.time.Duration;
import java.util.concurrent.ExecutionException;

@Service
public class ProducerService {

    private Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private KafkaTemplate kafkaTemplate;

    @Autowired
    KafkaProducerConfigProperties producerConfig;

    public void sendMessage(String topic, Object msg) throws ExecutionException, InterruptedException, KafkaConfigurationException {

        AdminClient adminClient = AdminClient.create(producerConfig.producerConfig());
        if (!adminClient.listTopics().names().get().stream().anyMatch(name -> name.equalsIgnoreCase(topic))) {
            LOGGER.error("topic [{}] does not exist", topic);
            throw new KafkaConfigurationException(String.format(": topic [%s] does not exist", topic), HttpStatus.BAD_REQUEST);
        }
        adminClient.close(Duration.ofSeconds(30));

        ListenableFuture<SendResult<String, Object>> future =
                kafkaTemplate.send(topic, msg);

        future.addCallback(new ListenableFutureCallback<SendResult<String, Object>>() {
            @Override
            public void onSuccess(SendResult<String, Object> result) {
                StringBuffer responseString = new StringBuffer(String.format("data published to kafka topic [%s], with with offset %s",
                        result.getRecordMetadata().topic(), result.getRecordMetadata().offset()));
                LOGGER.info("data published to kafka topic [{}], with with offset {}",
                        result.getRecordMetadata().topic(), result.getRecordMetadata().offset());
            }

            @Override
            public void onFailure(Throwable ex) {
                LOGGER.warn("Failed to publish message [{}] with message: {}",
                        msg, ex.getMessage());
            }
        });
    }

}
