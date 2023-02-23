package com.tyatsura.spring.integration.database.repository;

import com.tyatsura.spring.database.entity.User;
import com.tyatsura.spring.database.repository.UserRepository;
import com.tyatsura.spring.integration.annotation.IT;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.time.LocalDate;

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

    @Test
    void checkFirst() {
        var topUser = userRepository.findTopByOrderByIdDesc();
        assertTrue(topUser.isPresent());
        topUser.ifPresent(user -> assertEquals(5L, user.getId()));
    }

    @Test
    void checkFirst3() {
        var allUsers = userRepository.findTop3ByBirthDateBeforeOrderByBirthDateDesc(LocalDate.now());
        assertEquals(3, allUsers.size());
    }

    @Test
    void checkSort() {
        //var sortByName = Sort.by("firstName").and(Sort.by("lastName"));
        var sort = Sort.sort(User.class);
        var sortByName = sort.by(User::getFirstname).and(sort.by(User::getLastname));
        var allUsers = userRepository.findTop3ByBirthDateBefore(LocalDate.now(), sortByName);
        assertEquals(3, allUsers.size());
    }

    @Test
    void checkPageable() {
        var sort = Sort.sort(User.class);
        //!!! starts from 0
        var page = userRepository.findAllBy(PageRequest.of(0, 2, sort.by(User::getId)));

        System.out.println("First page:");
        page.forEach(System.out::println);
        while (page.hasNext()) {
            page = userRepository.findAllBy(page.nextPageable());
            System.out.println("Next page");
            page.forEach(System.out::println);
        }

        User lastUser = page.stream()
                             .reduce((first, second) -> second)
                             .orElse(null);
        assertNotNull(lastUser);
        assertEquals(5, lastUser.getId());
    }
}