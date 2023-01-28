package com.tyatsura.spring.config;

import com.tyatsura.spring.config.condition.JpaCondition;
import jakarta.annotation.PostConstruct;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

@Conditional(JpaCondition.class)
@Configuration
public class CustomJpaConfiguration {

    @Bean
    @ConfigurationProperties(prefix = "db", ignoreInvalidFields = true)
    public static CustomDatabaseProperties customDatabaseProperties() {
        return new CustomDatabaseProperties();
    }

    @PostConstruct
    void init() {
        System.out.println("Custom JPA configuration is enabled");
    }
}
