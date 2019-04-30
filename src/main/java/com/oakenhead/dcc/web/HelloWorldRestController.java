package com.oakenhead.dcc.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldRestController {

    @RequestMapping("/")
    public String index() {
        return "Greetings from Spring Boot!";
    }

}