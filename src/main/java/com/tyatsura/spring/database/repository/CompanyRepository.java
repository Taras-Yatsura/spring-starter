package com.tyatsura.spring.database.repository;

import com.tyatsura.spring.database.pool.ConnectionPool;
import lombok.ToString;

@ToString
public class CompanyRepository {
    private final ConnectionPool connectionPool;

    private CompanyRepository(ConnectionPool connectionPool) {
        this.connectionPool = connectionPool;
    }

    public static CompanyRepository of(ConnectionPool connectionPool){
        return new CompanyRepository(connectionPool);
    }
}
