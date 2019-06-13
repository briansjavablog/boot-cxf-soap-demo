package com.blog.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages= { "com.blog.demo.config", "com.blog.demo.service" })
public class Application {
    
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}