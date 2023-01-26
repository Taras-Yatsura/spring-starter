package com.tyatsura.spring;

import com.tyatsura.spring.database.pool.ConnectionPool;
import com.tyatsura.spring.database.repository.CompanyRepository;
import com.tyatsura.spring.database.repository.MoneyRepository;
import com.tyatsura.spring.database.repository.UserRepository;
import com.tyatsura.spring.ioc.Container;
import com.tyatsura.spring.service.UserService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ApplicationRunner {
    public static void main(String[] args) {
        try(var applicationContext = new ClassPathXmlApplicationContext("application.xml")) {
            //get by type -> Map<String, Object>
            //will be thrown org.springframework.beans.factory.NoUniqueBeanDefinitionException
            //System.out.println(applicationContext.getBean(ConnectionPool.class));

            //may be used just getBean(String name) but will be returned Object
            System.out.println(applicationContext.getBean("pool1", ConnectionPool.class));

            var companyRepository = applicationContext.getBean("companyRepository", CompanyRepository.class);
            System.out.println(companyRepository);

            var userRepository = applicationContext.getBean("userRepository", UserRepository.class);
            System.out.println(userRepository);

            var moneyRepo = applicationContext.getBean("moneyRepository", MoneyRepository.class);
            System.out.println(moneyRepo);
        }

        //To call destroy on beans app context should be closed or as it implements Autocloseable use try with resources
        //applicationContext.close();
    }
}
