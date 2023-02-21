package com.tyatsura.spring.integration.database.repository;

import com.tyatsura.spring.database.entity.Company;
import com.tyatsura.spring.integration.annotation.IT;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.test.annotation.Commit;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

/**
 * If no custom rollback rules are configured in this annotation, the transaction will roll back on RuntimeException
 * or Error but not on checked exceptions.<br>
 * Here used TransactionalTestExecutionListener.<br>
 * Also by default will be used @Rollback.<br>
 * By default used <b>Propagation.REQUIRED</b> - if there no any transactions it will open new one. Transaction will be
 * closed by that method or class that opened it. <br>
 * Another propagations: <ul>
 *      <li><b>SUPPORTS</b> - transaction will not be opened if there no any.</li>
 *      <li><b>MANDATORY</b> - will be throws exception if any transaction not opened.</li>
 *      <li><b>REQUIRES_NEW</b> - will open new transaction every time and pause previous if it exists.</li>
 *      <li><b>NOT_SUPPORTED</b> - without transaction and pause previous. (for cases when we do no need rollback if
 *      method throw exception)</li>
 *      <li><b>NEVER</b> - without transaction and thhrows exception if transaction already opened.</li>
 *      <li><b>NESTED</b> - use savepoint and works only with JDBC TM. Not supported by default with JPA TM. All
 *      changes before savepoint will be committed.</li>
 * </ul>
 * For <b>Propagation.REQUIRED</b> and <b>REQUIRES_NEW</b> will be used also
 * {@link org.springframework.transaction.annotation.Isolation} level: <ul>
 *     <li><b>DEFAULT</b> - level that used by DB by default (for PostgreSQL - READ_COMMITTED)</li>
 *     <li><b>READ_UNCOMMITTED</b> - no restrictions (may appear dirty reads, non-repeatable reads, and phantom
 *     reads.</li>
 *     <li><b>READ_COMMITTED</b> - dirty reads are prevented; non-repeatable reads and phantom reads can occur</li>
 *     <li><b>REPEATABLE_READ</b> - dirty reads and non-repeatable reads are prevented; phantom reads can occur</li>
 *     <li><b>SERIALIZABLE</b> - dirty reads and non-repeatable reads are prevented; phantom reads can occur</li>
 * </ul>
 * For transactions readOnly flag is wherry useful - some optimizations on DB and JAVA level will be used for such
 * transactions (PostgreSQL haven't such except that there not data writing, Hibernate - flush not used (very expensive
 * operation). <br>
 * Also take a look for useful options scu as {@link Transactional#rollbackFor()} and
 * {@link Transactional#noRollbackFor()} for custom logic when we should rollback transactions.
 * @see Transactional
 * @see Rollback
 * @see Commit
 * @see org.springframework.transaction.annotation.Propagation
 * @see org.springframework.transaction.annotation.Isolation
 * @see org.springframework.test.context.transaction.TransactionalTestExecutionListener
 */
@IT
@RequiredArgsConstructor
@Transactional
@Commit
public class CompanyRepositoryTest {
    /**
     * EntityManager - proxy that created with aspects using CGLib
     */
    private final EntityManager entityManager;

    /**
     * Direct using of EM in Spring application <b><u>not allowed</u></b>. This is only explanation. <br>
     * Before using of Spring we must open transactions directly in such cases where we have lazy initialization.
     * After first call to DB transaction is already closed and we will get LazyInitializationException ... could not
     * initialize proxy - no Session
    */
    @Test
    void findById() {
        /*var transaction = entityManager.getTransaction();
        transaction.begin();*/
        var company = entityManager.find(Company.class, 1);
        assertNotNull(company);
        assertEquals(2, company.getLocales().size());
        /*transaction.rollback();*/
    }

    @Test
    void save() {
        var company = Company.builder()
                .name("Apple")
                .locales(Map.of(
                        "ru", "Apple Описание",
                        "en", "Apple description"
                ))
                .build();
        entityManager.persist(company);
        assertNotNull(company.getId());
    }
}
