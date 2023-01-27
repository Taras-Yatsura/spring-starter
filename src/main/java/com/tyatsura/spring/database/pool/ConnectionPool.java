package com.tyatsura.spring.database.pool;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

//First calling @PostConstruct and @PreDestroy
//PreDestroy and analogues calling only for singleton beans because prototypes not saved in application context
@Component("pool1")
@ToString
public class ConnectionPool {
    private final String username;
    private final Integer poolSize;

    public ConnectionPool(@Value("${db.username}") String username,
                          @Value("${db.pool.size}") Integer poolSize) {
        this.username = username;
        this.poolSize = poolSize;
    }

    @PostConstruct
    private void init() {
        System.out.println("@PostConstruct of " + ConnectionPool.class.getSimpleName());
    }

    @PreDestroy
    private void terminate() {
        System.out.println("Destroying using @PreDestroy of " + ConnectionPool.class.getSimpleName());
    }
}
