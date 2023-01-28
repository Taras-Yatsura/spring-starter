package com.tyatsura.spring.service;

import com.tyatsura.spring.database.entity.Money;
import com.tyatsura.spring.database.repository.CRUDRepository;
import com.tyatsura.spring.dto.MoneyReadDto;
import com.tyatsura.spring.listener.entity.AccessType;
import com.tyatsura.spring.listener.entity.EntityEvent;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.Optional;

@ToString
@Service
@RequiredArgsConstructor
public class MoneyService {
    private final CRUDRepository<Integer, Money> moneyRepository;
    private final UserService userService;
    private final ApplicationEventPublisher eventPublisher;

    public Optional<MoneyReadDto> findById(Integer id) {
        return moneyRepository
                .findById(id)
                .map(money -> {
                    eventPublisher.publishEvent(new EntityEvent(money, AccessType.READ));
                    return new MoneyReadDto(money.id());
                });
    }
}
