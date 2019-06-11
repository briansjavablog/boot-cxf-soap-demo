package com.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages= { "com.demo.config", "com.demo.service" })
public class Application {
    
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}