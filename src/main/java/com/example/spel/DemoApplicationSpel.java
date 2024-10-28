package com.example.spel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@SpringBootApplication(scanBasePackages = "com.*.spel")
public class DemoApplicationSpel {
    @Autowired SpelBean bean;
    public static void main(String[] args) {
        SpringApplication.run(DemoApplicationSpel.class, args);
    }
}

@Component
class SpelBean {
}
