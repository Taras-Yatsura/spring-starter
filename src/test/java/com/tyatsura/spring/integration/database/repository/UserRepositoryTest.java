package com.tyatsura.spring.integration.database.repository;

import com.tyatsura.spring.database.entity.User;
import com.tyatsura.spring.database.repository.UserRepository;
import com.tyatsura.spring.integration.annotation.IT;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@IT
@Slf4j
@RequiredArgsConstructor
class UserRepositoryTest {
    private final UserRepository userRepository;

    /**
     * In case when we use LIKE in {@code @Query} we should add manually {@code %} characters in string that will be
     * used near it.
     */
    @Test
    void findAllBy() {
        var users = userRepository.findAllBy("a", "ov");
        assertEquals(3, users.size());
        log.info("Users list: {}", users);
    }

    @Test
    void findAllByName() {
        var users = userRepository.findAllByUsername("ivan@gmail.com");
        assertEquals(1, users.size());
        log.info("Users: {}", users);
    }

    @Test
    void updateRole() {
        var ivanUser = userRepository.getReferenceById(1L);
        assertEquals(User.Role.ADMIN, ivanUser.getRole());

        var resultCount = userRepository.updateRole(User.Role.USER, 1L, 5L);
        assertEquals(2, resultCount);

        //LazyInitializationException will be here because PersistenceContext already cleared and there no any
        // session attached to session
        //ivanUser.getCompany().getName();

        var theSameIvanUser = userRepository.getReferenceById(1L);
        assertEquals(User.Role.USER, theSameIvanUser.getRole());
    }
}