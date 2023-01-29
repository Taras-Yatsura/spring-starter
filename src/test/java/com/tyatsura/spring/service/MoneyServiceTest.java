package com.tyatsura.spring.service;

import com.tyatsura.spring.database.entity.Money;
import com.tyatsura.spring.database.repository.CRUDRepository;
import com.tyatsura.spring.dto.MoneyReadDto;
import com.tyatsura.spring.listener.entity.EntityEvent;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.ApplicationEventPublisher;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MoneyServiceTest {
    private static final Integer MONEY_ID = 1;
    @Mock
    private CRUDRepository<Integer, Money> moneyRepository;
    @Mock
    private UserService userService;
    @Mock
    private ApplicationEventPublisher eventPublisher;

    @InjectMocks
    private MoneyService moneyService;

    @Test
    void findById() {
        doReturn(Optional.of(new Money(MONEY_ID)))
               .when(moneyRepository)
               .findById(MONEY_ID);

        var actualResult = moneyService.findById(MONEY_ID);

        assertTrue(actualResult.isPresent());

        var expectedResult = new MoneyReadDto(MONEY_ID);
        actualResult.ifPresent(actual -> assertEquals(expectedResult, actual));

        verify(eventPublisher).publishEvent(any(EntityEvent.class));
        verifyNoMoreInteractions(eventPublisher, userService);
    }

}