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

@IT
@RequiredArgsConstructor
// If no custom rollback rules are configured in this annotation, the transaction will roll back on RuntimeException
// and Error but not on checked exceptions.
// Here used TransactionalTestExecutionListener
@Transactional
// @Rollback used by default
@Commit
public class CompanyRepositoryTest {
    //EM - proxy
    private final EntityManager entityManager;

    /*
    Direct using of EM in Spring application not allowed. This is only explanation
    Before using of Spring we must open transactions directly in such cases where we have lazy initialization because
    after first call to DB transaction is already closed and we will get LazyInitializationException ... could not
    initialize proxy - no Session
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
