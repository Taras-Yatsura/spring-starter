package com.tyatsura.spring.database.repository;

import com.tyatsura.spring.bpp.Auditing;
import com.tyatsura.spring.bpp.Operation;
import com.tyatsura.spring.database.entity.Money;
import com.tyatsura.spring.database.pool.ConnectionPool;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Slf4j
@Repository
@Operation
@Auditing
@ToString
@RequiredArgsConstructor
public class MoneyRepository implements CRUDRepository<Integer, Money> {
    @Qualifier("pool2")
    private final ConnectionPool connectionPool;

    @PostConstruct
    private void init() {
        log.debug("Initialized");
    }

    @Override
    public Optional<Money> findById(Integer id) {
        log.debug("Find by id {} method", id);
        return Optional.of(new Money(id));
    }

    @Override
    public void delete(Money entity) {
        log.debug("Delete {} method", entity);
    }
}
