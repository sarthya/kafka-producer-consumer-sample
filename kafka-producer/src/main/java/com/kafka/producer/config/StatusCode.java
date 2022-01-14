package com.kafka.producer.config;

public enum StatusCode {
    SUCCESSFULLY_PUBLISHED("published data", 201),
    FAILED_PUBLISH("failed to publish", 400);

    String statusText;
    Integer code;

    StatusCode(String s, Integer i) {
        statusText = s;
        code = i;
    }

    public Integer code() {
        return code;
    }

    public String statusText() {
        return statusText;
    }
}
