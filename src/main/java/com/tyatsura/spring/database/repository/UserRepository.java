package com.tyatsura.spring.database.repository;

import com.tyatsura.spring.database.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    /**
     * Spring Data may use % with like. Not HQL standard.
     * @param firstName regex for first name
     * @param lastName regex for last name
     * @return list of users that fit regex
     */
    //@Query("SELECT u FROM User u where u.firstname LIKE ?1 and u.lastname LIKE ?2")
    @Query("SELECT u FROM User u where u.firstname LIKE %?1% and u.lastname LIKE %?2%")
    List<User> findAllBy(String firstName, String lastName);

    @Query(nativeQuery = true, value = "SELECT u.* FROM users u WHERE u.username = :username")
    List<User> findAllByUsername(String username);
}
