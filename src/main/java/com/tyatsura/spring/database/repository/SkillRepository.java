package com.tyatsura.spring.database.repository;

import com.tyatsura.spring.database.pool.ConnectionPool;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

@Repository
@ToString
@RequiredArgsConstructor
public class SkillRepository {
    @Qualifier("pool1")
    private final ConnectionPool connectionPool1;
    @Qualifier("pool2")
    private final ConnectionPool connectionPool2;
}
