package com.tyatsura.spring.database.repository;

import com.tyatsura.spring.bpp.Auditing;
import com.tyatsura.spring.bpp.InjectBean;
import com.tyatsura.spring.bpp.Operation;
import com.tyatsura.spring.database.entity.Money;
import com.tyatsura.spring.database.pool.ConnectionPool;
import jakarta.annotation.PostConstruct;
import lombok.ToString;

import java.util.Optional;

@Operation
@Auditing
@ToString
public class MoneyRepository implements CRUDRepository<Integer, Money> {
    @InjectBean
    private ConnectionPool connectionPool;

    @PostConstruct
    private void init() {
        System.out.printf("%s is initialized\n", MoneyRepository.class.getSimpleName());
    }

    @Override
    public Optional<Money> findById(Integer id) {
        System.out.printf("Find by id %d method\n", id);
        return Optional.of(new Money(id));
    }

    @Override
    public void delete(Money entity) {
        System.out.printf("Delete %s method\n", entity);
    }
}
