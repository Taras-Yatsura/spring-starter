package com.tyatsura.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

//Clear this class and delete application.xml also as it used by spring boot
//Also can be used Spring starter from site and from IntelliJ Idea
//this class should be placed in root package and allowed only one annotation
@ConfigurationPropertiesScan
@SpringBootApplication
public class ApplicationRunner {
    public static void main(String[] args) {
        var context = SpringApplication.run(ApplicationRunner.class, args);
        System.out.println(context.getBean("pool1"));
    }
}
