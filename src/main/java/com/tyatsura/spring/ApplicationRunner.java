package com.tyatsura.spring;

import com.tyatsura.spring.database.pool.ConnectionPool;
import com.tyatsura.spring.database.repository.CompanyRepository;
import com.tyatsura.spring.database.repository.UserRepository;
import com.tyatsura.spring.ioc.Container;
import com.tyatsura.spring.service.UserService;

public class ApplicationRunner {
    public static void main(String[] args) {
        Container container = new Container();
        /*ConnectionPool connectionPool = container.get(ConnectionPool.class);
        UserRepository userRepository = container.get(UserRepository.class);
        CompanyRepository companyRepository = container.get(CompanyRepository.class);*/
        UserService userService = container.get(UserService.class);


        /*ConnectionPool connectionPool = new ConnectionPool();
        UserRepository userRepository = new UserRepository(connectionPool);
        CompanyRepository companyRepository = new CompanyRepository(connectionPool);
        UserService userService = new UserService(userRepository, companyRepository);*/
        //TODO userService
    }
}
