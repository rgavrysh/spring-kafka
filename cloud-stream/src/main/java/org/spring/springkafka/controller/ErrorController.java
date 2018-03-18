package org.spring.springkafka.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ErrorController {

    @GetMapping("/no-method")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void noMethod() {
        throw new NoSuchMethodError("No such method error");
    }

    @GetMapping("/illegal-argument")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void illegalArgument() {
        throw new IllegalArgumentException("Illegal argument provided");
    }

    @GetMapping("/access")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void illegalAccess() {
        throw new IllegalAccessError("No access granted");
    }
}
