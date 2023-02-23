package com.tyatsura.spring.config;

import com.tyatsura.spring.database.pool.ConnectionPool;
import com.tyatsura.spring.database.repository.UserRepository;
import com.tyatsura.web.config.WebConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;

@Configuration
//@PropertySource also have default definition in @SpringBootApplication (will be used application.properties
//used @ComponentScan from @SpringBootConfiguration that already is @Configuration and has default component scan
// already configured
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
}
