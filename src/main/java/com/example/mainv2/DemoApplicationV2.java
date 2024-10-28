package com.example.mainv2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringApplicationHook;
import org.springframework.boot.SpringApplicationRunListener;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.metrics.ApplicationStartup;
import org.springframework.core.metrics.StartupStep;

import java.util.Collections;
import java.util.Iterator;
import java.util.function.Supplier;

@SpringBootApplication
public class DemoApplicationV2 {
    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication(DemoApplicationV2.class);
        springApplication.setApplicationStartup(new ExplytApplicationStartup());
        SpringApplicationHook applicationHook = application -> new ExplytSpringApplicationRunListener();
        SpringApplication.withHook(applicationHook, () -> springApplication.run(args));
    }

    private static class ExplytSpringApplicationRunListener implements SpringApplicationRunListener {
        @Override
        public void failed(ConfigurableApplicationContext context, Throwable exception) {
            //printBeans(context);
        }
    }

    private static class ExplytApplicationStartup implements ApplicationStartup {

        @Override
        public ExplytDefaultStartupStep start(String name) {
            return new ExplytDefaultStartupStep(name);
        }

        class ExplytDefaultStartupStep implements StartupStep {

            private final ExplytDefaultStartupStep.DefaultTags TAGS = new DefaultTags();
            private String stepName;

            public ExplytDefaultStartupStep(String name) {
                this.stepName = name;
            }

            @Override
            public String getName() {
                return "default";
            }

            @Override
            public long getId() {
                return 0L;
            }

            @Override
            public Long getParentId() {
                return null;
            }

            @Override
            public Tags getTags() {
                return this.TAGS;
            }

            @Override
            public StartupStep tag(String key, String value) {
                return this;
            }

            @Override
            public StartupStep tag(String key, Supplier<String> value) {
                return this;
            }

            @Override
            public void end() {
                if ("spring.context.beans.post-process".equalsIgnoreCase(stepName)) {
                    throw new RuntimeException("I am Explyt Plugin!!!");
                }
            }

            class DefaultTags implements StartupStep.Tags {

                @Override
                public Iterator<Tag> iterator() {
                    return Collections.emptyIterator();
                }
            }
        }
    }
}

