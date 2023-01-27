package com.tyatsura.spring.database.repository;

import com.tyatsura.spring.database.pool.ConnectionPool;
import jakarta.annotation.Resource;
import lombok.Setter;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

@ToString
public class SkillRepository {
    private final ConnectionPool connectionPoolAutowired;
    //here is using lombok setter with setting @Resource annotation on it
    //@Resource can not be used with constructor (with field, method, type only) that means that field not immutable
    //used only for compatibility with JSR-250
    //also Resource have name for Qualifier as parameter
    @Setter(onMethod = @__({@Resource(name = "pool2")}))
    private ConnectionPool connectionPoolResource;

    //Autowired should use @Qualifier if context have more than one Bean
    //or should be used field with same name as bean
    //or if we need many bean with same type we can inject such beans in collection
    @Autowired
    public SkillRepository(@Qualifier("pool1") ConnectionPool connectionPoolAutowired) {
        this.connectionPoolAutowired = connectionPoolAutowired;
    }


}
