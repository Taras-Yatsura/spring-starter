package com.tyatsura.spring.database.repository;

import com.tyatsura.spring.database.entity.Company;
import org.springframework.data.repository.Repository;

import java.util.Optional;

/**
 * Here used {@link org.springframework.boot.autoconfigure.data.jpa.JpaRepositoriesAutoConfiguration}
 */
public interface CompanyRepository extends Repository<Company, Integer> {
    Optional<Company> findById(Integer id);
    void delete(Company company);
}
