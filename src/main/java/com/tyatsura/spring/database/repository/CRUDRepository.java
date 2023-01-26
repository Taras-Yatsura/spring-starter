package com.tyatsura.spring.database.repository;

import java.util.Optional;

public interface CRUDRepository<K, E> {
    Optional<E> findById(K id);
    void delete(E entity);
}
