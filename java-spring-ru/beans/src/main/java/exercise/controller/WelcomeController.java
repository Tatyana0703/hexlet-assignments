package exercise.controller;

import exercise.daytime.Daytime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
public class WelcomeController {

    @Autowired
    private Daytime daytime;

    @GetMapping(path = "/welcome")
    @ResponseStatus(HttpStatus.OK)
    public String welcome() {
        return String.format("It is %s now! Welcome to Spring!", daytime.getName());
    }
}
