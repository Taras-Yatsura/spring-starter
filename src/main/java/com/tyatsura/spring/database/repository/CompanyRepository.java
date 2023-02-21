package com.tyatsura.spring.database.repository;

import com.tyatsura.spring.database.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**
 * Here used {@link org.springframework.boot.autoconfigure.data.jpa.JpaRepositoriesAutoConfiguration}. <br>
 * {@link org.springframework.data.repository.CrudRepository} already have realizations for
 * (find|count|exists|delete)[anyWord][(By)ParameterInDb] (look at
 * {@link org.springframework.data.repository.query.parser.PartTree} for more info). In such way can be used almost
 * all methods except update operations.
 * @see org.springframework.data.repository.CrudRepository
 * @see JpaRepository
 * @see org.springframework.data.repository.query.parser.PartTree
 */
public interface CompanyRepository extends JpaRepository<Company, Integer> {
    /**
     * Spring will create criteria query with criteria API and connect to method (it have {@code Map<MethodName, Query>}
     * where it will be stored and added to class when Spring up Spring context. For this purposes will be used AOP
     * dynamic proxy and that why interfaces are used. Look at
     * {@link org.springframework.data.repository.core.support.QueryExecutorMethodInterceptor#queries} <br>
     * Return value may be {@code Optional<Entity>, Entity, Future<Entity>}
     * @param name field that presents in table in DB
     * @return {@code Optional<Company>}
     */
    Optional<Company> findByName(String name);

    /**
     * Here we create query that will look entities by parameter with regex that used in LIKE SQL operator. Another
     * keywords that will be parsed and recognized see
     * <a href="https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#repository-query-keywords">here</a>
     * @param fragment parameter that will be used in like
     * @return List but also may be used Collection, Stream(batch, close)
     */
    List<Company> findByNameContainingIgnoreCase(String fragment);
}
