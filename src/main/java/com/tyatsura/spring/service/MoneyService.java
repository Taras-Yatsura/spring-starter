package com.tyatsura.spring.service;

import com.tyatsura.spring.database.entity.Money;
import com.tyatsura.spring.database.repository.CRUDRepository;
import com.tyatsura.spring.dto.MoneyReadDto;
import com.tyatsura.spring.listener.entity.AccessType;
import com.tyatsura.spring.listener.entity.EntityEvent;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.Optional;

@ToString
@Service
public class MoneyService {
    private final CRUDRepository<Integer, Money> moneyRepository;
    private final UserService userService;
    private final ApplicationEventPublisher eventPublisher;

    @Autowired
    public MoneyService(CRUDRepository<Integer, Money> moneyRepository,
                        UserService userService, ApplicationEventPublisher eventPublisher) {
        this.moneyRepository = moneyRepository;
        this.userService = userService;
        this.eventPublisher = eventPublisher;
    }

    public Optional<MoneyReadDto> findById(Integer id) {
        return moneyRepository
                .findById(id)
                .map(money -> {
                    eventPublisher.publishEvent(new EntityEvent(money, AccessType.READ));
                    return new MoneyReadDto(money.id());
                });
    }
}
