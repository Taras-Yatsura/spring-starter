package com.tyatsura.spring.integration.service;

import com.tyatsura.spring.dto.MoneyReadDto;
import com.tyatsura.spring.integration.annotation.IT;
import com.tyatsura.spring.service.MoneyService;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


@IT
@RequiredArgsConstructor
//used property from spring.properties
// @TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
public class MoneyServiceIT {
    private static final Integer MONEY_ID = 1;

    @Autowired
    private final MoneyService moneyService;

     @Test
    void findById() {
         var actualResult = moneyService.findById(MONEY_ID);

         assertTrue(actualResult.isPresent());

         var expectedResult = new MoneyReadDto(MONEY_ID);
         actualResult.ifPresent(actual -> assertEquals(expectedResult, actual));
     }
}
