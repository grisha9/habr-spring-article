package com.example.conditional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.context.annotation.Conditional;
import org.springframework.core.type.AnnotatedTypeMetadata;
import org.springframework.stereotype.Component;

@SpringBootApplication
public class DemoApplicationConditional {
    @Autowired ConditionalBean bean;

    public static void main(String[] args) {
        SpringApplication.run(DemoApplicationConditional.class, args);
    }
}


@Conditional(MyCondition.class)
@Component
class ConditionalBean {}

class MyCondition implements Condition {
    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        //same user logic
        return false;
    }
}