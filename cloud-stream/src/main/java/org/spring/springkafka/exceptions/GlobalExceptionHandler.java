package org.spring.springkafka.exceptions;

import org.spring.springkafka.model.ErrorModel;
import org.spring.springkafka.service.ErrorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @Autowired
    private ErrorService errorService;

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(Throwable.class)
    public ErrorModel handleException(Throwable e) {
        ErrorModel error = new ErrorModel(System.currentTimeMillis(), e.getLocalizedMessage());
        errorService.sendError(error);
        return error;
    }
}
