package com.tyatsura.spring.database.repository;

import com.tyatsura.spring.database.pool.ConnectionPool;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

@Repository
@ToString
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
@RequiredArgsConstructor
public class UserRepository {
    @Qualifier("pool1")
    private final ConnectionPool connectionPool;
}
