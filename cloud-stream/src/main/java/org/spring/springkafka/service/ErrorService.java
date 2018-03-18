package org.spring.springkafka.service;

import org.spring.springkafka.model.ErrorModel;
import org.spring.springkafka.stream.ErrorStream;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;
import org.springframework.util.MimeTypeUtils;

@Service
public class ErrorService {
    private final ErrorStream errorStream;

    public ErrorService(ErrorStream errorStream) {
        this.errorStream = errorStream;
    }

    public void sendError(final ErrorModel errorModel) {
        MessageChannel messageChannel = errorStream.outboundErrors();
        messageChannel.send(MessageBuilder
                .withPayload(errorModel)
                .setHeader(MessageHeaders.CONTENT_TYPE, MimeTypeUtils.APPLICATION_JSON)
                .build());
    }
}
