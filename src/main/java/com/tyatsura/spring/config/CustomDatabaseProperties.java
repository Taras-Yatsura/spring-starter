package com.tyatsura.spring.config;

import lombok.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.bind.ConstructorBinding;

import java.util.List;
import java.util.Map;

//Object should be POJO
@Value
//@ConstructorBinding
//to work use @Component annotation or @ConfigurationPropertyScan on ApplicationRunner class
//Also can be used Record
@ConfigurationProperties(prefix = "db")
public class CustomDatabaseProperties {
    String username;
    String password;
    String driver;
    String url;
    String hosts;
    PoolProperties pool;
    List<PoolProperties> pools;
    Map<String, Object> properties;

    @Value
    public static class PoolProperties {
        Integer size;
        Integer timeout;
    }
}
