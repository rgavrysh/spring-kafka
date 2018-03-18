package org.spring.springkafka.service;

import org.spring.springkafka.model.ErrorModel;
import org.spring.springkafka.stream.ErrorStream;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
public class ErrorListener {

    @StreamListener(ErrorStream.INPUT)
    public void logErrors(@Payload ErrorModel errorModel) {
        System.out.println(errorModel.toString());
    }
}
