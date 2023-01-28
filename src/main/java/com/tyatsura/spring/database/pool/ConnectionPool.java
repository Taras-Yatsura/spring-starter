package com.tyatsura.spring.database.pool;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

//First calling @PostConstruct and @PreDestroy
//PreDestroy and analogues calling only for singleton beans because prototypes not saved in application context
@Slf4j
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
        log.debug("@PostConstruct");
    }

    @PreDestroy
    private void terminate() {
        log.debug("@PreDestroy");
    }
}
