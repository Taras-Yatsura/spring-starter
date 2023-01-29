package com.tyatsura.spring.integration.service;

import com.tyatsura.spring.database.pool.ConnectionPool;
import com.tyatsura.spring.integration.service.annotation.IT;
import com.tyatsura.spring.service.UserService;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;

@IT
@RequiredArgsConstructor
//@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class UserServiceIT {
    private final UserService userService;
    private final ConnectionPool pool1;

    @Test
    void test() {

    }
}
