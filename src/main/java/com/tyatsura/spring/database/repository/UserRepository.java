package com.tyatsura.spring.database.repository;

import com.tyatsura.spring.database.pool.ConnectionPool;
import lombok.Setter;
import lombok.ToString;

@ToString
public class UserRepository {
    //use setter for injection beans
    //this approach is bad because object not immutable
    //and cause cyclic dependence of two beans
    //used only for optional beans
    @Setter
    private ConnectionPool connectionPool;

    public UserRepository(ConnectionPool connectionPool) {
        this.connectionPool = connectionPool;
    }
}
