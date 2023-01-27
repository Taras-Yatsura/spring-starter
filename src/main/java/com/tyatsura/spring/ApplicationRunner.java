package com.tyatsura.spring;

import com.tyatsura.spring.database.pool.ConnectionPool;
import com.tyatsura.spring.database.repository.*;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ApplicationRunner {
    public static void main(String[] args) {
        try(var applicationContext = new ClassPathXmlApplicationContext("application.xml")) {
            //get by type -> Map<String, Object>
            //will be thrown org.springframework.beans.factory.NoUniqueBeanDefinitionException
            //System.out.println(applicationContext.getBean(ConnectionPool.class));

            //may be used just getBean(String name) but will be returned Object
            var pool1 = applicationContext.getBean("pool1", ConnectionPool.class);
            System.out.println(pool1);

            var companyRepository = applicationContext.getBean("companyRepository", CompanyRepository.class);
            System.out.println(companyRepository);

            var userRepository = applicationContext.getBean("userRepository", UserRepository.class);
            System.out.println(userRepository);

            //as moneyRepository now dynamic proxy - will be thrown exception that such bean not found if use
            // MoneyRepository class. With Proxy by inheritance we don't get such problems
            var moneyRepo = applicationContext.getBean("moneyRepository", CRUDRepository.class);
            System.out.println(moneyRepo);
            System.out.println(moneyRepo.findById(1));

            var skillRepo = applicationContext.getBean("skillRepository", SkillRepository.class);
            System.out.println(skillRepo);
        }

        //To call destroy on beans app context should be closed or as it implements Autocloseable use try with resources
        //applicationContext.close();
    }
}
