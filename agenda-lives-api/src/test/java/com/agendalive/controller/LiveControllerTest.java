package com.agendalive.controller;

import com.agendalive.mock.LiveMock;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@ActiveProfiles("test")
@SpringBootTest
@RunWith(SpringRunner.class)
class LiveControllerTest {

    private static final String BASE_URL = "/api/live";

    @Autowired
    private LiveMock liveMock;

    @Test
    void findLives() throws Exception {
        liveMock.persistLive();
    }
}
