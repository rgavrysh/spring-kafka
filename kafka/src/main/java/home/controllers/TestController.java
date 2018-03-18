package home.controllers;

import home.services.ErrorService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
public class TestController {

    private final ErrorService errorService;

    public TestController(ErrorService errorService) {
        this.errorService = errorService;
    }

    @GetMapping("/")
    public String getRoot() {
        return "Login Page!";
    }

    @GetMapping("/home")
    public String getHome() {
        return "Home Page!";
    }

    @GetMapping("/exception")
    public String getException() throws NoSuchMethodException {
        throw new NoSuchMethodException();
    }

    @GetMapping("/errors")
    public Collection<String> getErrors() {
        return errorService.getErrors();
    }
}
