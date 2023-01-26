package com.tyatsura.spring.database.pool;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import lombok.ToString;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

import java.util.List;
import java.util.Map;

//First calling @PostConstruct and @PreDestroy
//PreDestroy and analogues calling only for singleton beans because prototypes not saved in application context
@ToString
public class ConnectionPool implements InitializingBean, DisposableBean {
    private final String username;
    private final Integer poolSize;
    private final List<Object> args;
    private final Map<String, Object> properties;

    public ConnectionPool(String username, Integer poolSize, List<Object> args, Map<String, Object> properties) {
        this.username = username;
        this.poolSize = poolSize;
        this.args = args;
        this.properties = properties;
    }

    @PostConstruct
    private void init() {
        System.out.println("@PostConstruct of " + ConnectionPool.class.getSimpleName());
    }

    //from the InitializingBean - not recommended to use - broke IoC
    //Called second
    @Override
    public void afterPropertiesSet() {
        System.out.println("PostConstruct using InitializingBean of " + ConnectionPool.class.getSimpleName());
    }

    @PreDestroy
    private void terminate() {
        System.out.println("Destroying using @PreDestroy of " + ConnectionPool.class.getSimpleName());
    }

    //Called second
    public void destroy() {
        System.out.println("Destroying using DisposableBean of " + ConnectionPool.class.getSimpleName());
    }
}
