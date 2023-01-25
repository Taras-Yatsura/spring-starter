package com.tyatsura.spring.database.repository;

import com.tyatsura.spring.database.pool.ConnectionPool;

public class CompanyRepository {
    private final ConnectionPool connectionPool;

    public CompanyRepository(ConnectionPool connectionPool) {
        this.connectionPool = connectionPool;
    }
}
