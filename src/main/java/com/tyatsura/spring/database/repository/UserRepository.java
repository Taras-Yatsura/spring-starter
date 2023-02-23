package com.tyatsura.spring.database.repository;

import com.tyatsura.spring.database.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
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

    /**
     * PersistenceContext by default will not be used for update operations. So in this case in will be cached for
     * read operations the old value and for correct work we should use parameters of {@code @Modifying} annotation:
     * flushAutomatically and clearAutomatically (PersistenceContext will be cleared after modifying operations in
     * case when we do not not entities that will be changed while modifying query). PersistenceContext have a role
     * of first level cache
     * @param role role to set for user that have ids in next arg
     * @param ids ids of users to set role
     * @return number of modified roles
     * @see Modifying
     */
    @Modifying(clearAutomatically = true, flushAutomatically = true)
    @Query("update User u set u.role = :role where u.id in (:ids)")
    int updateRole(User.Role role, Long... ids);
}
