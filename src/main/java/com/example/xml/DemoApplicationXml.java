package com.example.xml;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ImportResource("classpath:example-xml.xml")
public class DemoApplicationXml {
    @Autowired XmlBean bean;
    public static void main(String[] args) {
        SpringApplication.run(DemoApplicationXml.class, args);
    }
}

class XmlBean {}