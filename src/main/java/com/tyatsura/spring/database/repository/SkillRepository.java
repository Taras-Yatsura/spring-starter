package com.tyatsura.spring.database.repository;

import com.tyatsura.spring.database.pool.ConnectionPool;
import jakarta.annotation.Resource;
import lombok.Setter;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

@Repository
@ToString
public class SkillRepository {
    private final ConnectionPool connectionPoolAutowired;
    /*private final ConnectionPool connectionPoolResource;*/

    //Autowired should use @Qualifier if context have more than one Bean
    //or should be used field with same name as bean
    //or if we need many bean with same type we can inject such beans in collection

    @Autowired
    public SkillRepository(@Qualifier("pool1") ConnectionPool connectionPoolAutowired/*,*/
                           /*@Qualifier("pool2") ConnectionPool connectionPoolResource*/) {
        this.connectionPoolAutowired = connectionPoolAutowired;
        /*this.connectionPoolResource = connectionPoolResource;*/
    }


}
