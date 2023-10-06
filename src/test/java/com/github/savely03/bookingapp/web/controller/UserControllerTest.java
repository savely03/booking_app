package com.github.savely03.bookingapp.web.controller;

import com.github.savely03.bookingapp.repository.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

@WithMockUser(username = "user", password = "qwerty")
class UserControllerTest extends BaseControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;

    @AfterEach
    void tearDown() {
        userRepository.deleteAll();
    }

    @Test
    void createTest() {
    }

    @Test
    void updateTest() {
    }

    @Test
    void findByIdTest() {
    }

    @Test
    void findAllTest() {
    }

    @Test
    void deleteByIdTest() {
    }


}