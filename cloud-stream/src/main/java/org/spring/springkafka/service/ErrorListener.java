package org.spring.springkafka.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.spring.springkafka.model.ErrorModel;
import org.spring.springkafka.stream.ErrorStream;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
public class ErrorListener {
    private final Logger logger = LoggerFactory.getLogger(ErrorListener.class);

    @StreamListener(ErrorStream.INPUT)
    public void logErrors(@Payload ErrorModel errorModel) {
        logger.info(errorModel.toString());
    }
}
