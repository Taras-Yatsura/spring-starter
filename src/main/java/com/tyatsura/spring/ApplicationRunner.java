package com.tyatsura.spring;

import com.tyatsura.spring.database.pool.ConnectionPool;
import com.tyatsura.spring.database.repository.CompanyRepository;
import com.tyatsura.spring.database.repository.UserRepository;
import com.tyatsura.spring.ioc.Container;
import com.tyatsura.spring.service.UserService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ApplicationRunner {
    public static void main(String[] args) {
        var applicationContext = new ClassPathXmlApplicationContext("application.xml");
        //get by type -> Map<String, Object>
        //will be thrown org.springframework.beans.factory.NoUniqueBeanDefinitionException
        //System.out.println(applicationContext.getBean(ConnectionPool.class));

        //may be used just getBean(String name) but will be returned Object
        System.out.println(applicationContext.getBean("pool1", ConnectionPool.class));

    }
}
