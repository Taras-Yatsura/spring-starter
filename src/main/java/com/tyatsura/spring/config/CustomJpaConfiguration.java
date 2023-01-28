package com.tyatsura.spring.config;

import com.tyatsura.spring.config.condition.JpaCondition;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Conditional(JpaCondition.class)
@Configuration
public class CustomJpaConfiguration {

    /*one of the methods to set custom yml file configuration
    @Bean
    @ConfigurationProperties(prefix = "db", ignoreInvalidFields = true)
    public static CustomDatabaseProperties customDatabaseProperties() {
        return new CustomDatabaseProperties();
    }*/

    @PostConstruct
    void init() {
        log.debug("Custom JPA configuration is enabled");
    }
}
