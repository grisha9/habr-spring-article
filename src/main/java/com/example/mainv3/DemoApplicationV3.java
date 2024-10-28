package com.example.mainv3;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.metrics.ApplicationStartup;
import org.springframework.core.metrics.StartupStep;

import java.util.Collections;
import java.util.Iterator;
import java.util.function.Supplier;

@SpringBootApplication
public class DemoApplicationV3 {
    public static void main(String[] args) {
        ExplytApplicationStartup applicationStartup = new ExplytApplicationStartup();
        SpringApplication springApplication = new SpringApplication(DemoApplicationV3.class) {
            @Override
            protected ConfigurableApplicationContext createApplicationContext() {
                applicationStartup.context = super.createApplicationContext();
                return applicationStartup.context;
            }
        };
        springApplication.setApplicationStartup(applicationStartup);
        springApplication.run(args);
    }

    private static class ExplytApplicationStartup implements ApplicationStartup {
        public ConfigurableApplicationContext context;

        @Override
        public ExplytDefaultStartupStep start(String name) {
            return new ExplytDefaultStartupStep(name);
        }

        class ExplytDefaultStartupStep implements StartupStep {

            private final DefaultTags TAGS = new DefaultTags();
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
                    //printBeans(context);
                    throw new RuntimeException("I am Explyt Plugin!!!");
                }
            }

            class DefaultTags implements Tags {

                @Override
                public Iterator<Tag> iterator() {
                    return Collections.emptyIterator();
                }
            }
        }
    }
}

