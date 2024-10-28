package com.example.simple;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.example.simple")
public class DemoApplicationSimple {
    public static void main(String[] args) {
        SpringApplication.run(DemoApplicationSimple.class, args);
    }
}