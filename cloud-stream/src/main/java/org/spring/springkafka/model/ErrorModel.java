package org.spring.springkafka.model;

import java.util.Date;

public final class ErrorModel {
    private final long timestamp;
    private final String errorMessage;

    public ErrorModel(long timestamp, String errorMessage) {
        this.timestamp = timestamp;
        this.errorMessage = errorMessage;
    }


    public long getTimestamp() {
        return timestamp;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    @Override
    public String toString() {
        return new Date(timestamp).toString() + ": " + errorMessage;
    }
}
