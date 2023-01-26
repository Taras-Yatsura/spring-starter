package com.tyatsura.spring.database.repository;

import com.tyatsura.spring.bpp.InjectBean;
import com.tyatsura.spring.database.pool.ConnectionPool;
import lombok.ToString;

@ToString
public class MoneyRepository {
    @InjectBean
    private ConnectionPool connectionPool;
}
