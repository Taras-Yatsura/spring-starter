package com.tyatsura.spring.service;

import com.tyatsura.spring.database.entity.Money;
import com.tyatsura.spring.database.repository.CRUDRepository;
import com.tyatsura.spring.database.repository.UserRepository;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@ToString
@Service
public class UserService {
    private final UserRepository userRepository;
    //here such because we use Dynamic Proxy
    private final CRUDRepository<Integer, Money> moneyRepository;

    //Qualifier can not be used if bean name same as constructor parameter
    @Autowired
    public UserService(@Qualifier("userRepository") UserRepository userRepository,
                       @Qualifier("moneyRepository") CRUDRepository<Integer, Money> moneyRepository) {
        this.userRepository = userRepository;
        this.moneyRepository = moneyRepository;
    }
}
