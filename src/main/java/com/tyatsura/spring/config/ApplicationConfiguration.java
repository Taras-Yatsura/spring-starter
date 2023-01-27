package com.tyatsura.spring.config;

import com.tyatsura.spring.database.repository.CRUDRepository;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Configuration
@PropertySource("classpath:application.properties")
@ComponentScan(basePackages = "com.tyatsura.spring",
               useDefaultFilters = false,
               includeFilters = {
                                @Filter(type = FilterType.ANNOTATION, value = Component.class),
                                @Filter(type = FilterType.ASSIGNABLE_TYPE, value = CRUDRepository.class),
                                @Filter(type = FilterType.REGEX, pattern = "com\\..+Repository")
               })
public class ApplicationConfiguration {

}
