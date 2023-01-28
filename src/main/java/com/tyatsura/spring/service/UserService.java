package com.tyatsura.spring.service;

import com.tyatsura.spring.database.entity.Money;
import com.tyatsura.spring.database.repository.CRUDRepository;
import com.tyatsura.spring.database.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@ToString
@Service
@RequiredArgsConstructor
public class UserService {
    @Qualifier("userRepository")
    private final UserRepository userRepository;
    //here such because we use Dynamic Proxy
    @Qualifier("moneyRepository")
    private final CRUDRepository<Integer, Money> moneyRepository;
}
