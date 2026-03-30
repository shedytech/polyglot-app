package com.example.javaservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

@RestController
@SpringBootApplication
public class App {

    @GetMapping("/")
    public String home() {
        return "Hello, i Vincent kombe Deployed this app from Java Service 🚀";
    }

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
}

