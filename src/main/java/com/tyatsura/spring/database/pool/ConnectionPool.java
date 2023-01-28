package com.tyatsura.spring.database.pool;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

//First calling @PostConstruct and @PreDestroy
//PreDestroy and analogues calling only for singleton beans because prototypes not saved in application context
@Component("pool1")
@ToString
@RequiredArgsConstructor
public class ConnectionPool {
    @Value("${db.username}")
    private final String username;
    @Value("${db.pool.size}")
    private final Integer poolSize;

    @PostConstruct
    private void init() {
        System.out.println("@PostConstruct of " + ConnectionPool.class.getSimpleName());
    }

    @PreDestroy
    private void terminate() {
        System.out.println("Destroying using @PreDestroy of " + ConnectionPool.class.getSimpleName());
    }
}
