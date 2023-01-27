package com.tyatsura.spring.config;

import com.tyatsura.spring.database.pool.ConnectionPool;
import com.tyatsura.spring.database.repository.CRUDRepository;
import com.tyatsura.spring.database.repository.UserRepository;
import com.tyatsura.web.config.WebConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;
import org.springframework.context.annotation.ComponentScan.Filter;
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
//for configuration with xml/groovy/property
//@ImportResource("classpath:application.xml")
//for scanning packages or classes from another modules, dependencies
@Import(WebConfiguration.class)
public class ApplicationConfiguration {

    //Can be used in another classes but as configuration classes scans first - beans should be declared here
    @Bean("pool2")
    public ConnectionPool pool2(@Value("${db.username}") String userName) {
        return new ConnectionPool(userName, 1);
    }

    @Bean("pool3")
    public ConnectionPool pool3() {
        return new ConnectionPool("testUser", 1);
    }

    //by default will be recreated first repo because used method name (as it Prototype)
    @Bean("userRepository2")
    @Profile("prod|web") //can be used ! & |
    @Autowired
    public UserRepository userRepository(@Qualifier("pool2") ConnectionPool connectionPool) {
        return new UserRepository(connectionPool);
    }

    @Bean("userRepository3")
    public UserRepository userRepository3() {
        // if set @Configuration(proxyBeanMethods = false) such bean creating not allowed because this turning off
        // Service Locator Pattern using for creating of beans with proxy. And will be created new object every time
        pool3();
        pool3();
        pool3();
        return new UserRepository(pool3());
    }
}
