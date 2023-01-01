package com.perfect.community.api.controller.post;

import com.perfect.community.api.controller.ControllerIntegrationTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.test.context.transaction.AfterTransaction;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class PostControllerTest extends ControllerIntegrationTest {

    @Autowired
    protected PostController controller;

    @BeforeEach
    void setUp() {
        setUp(controller);
    }

    @AfterTransaction
    void relocateBoardNumbers() {
        log.info("auto_increment key value: " + relocateService.relocateBoardNumbers("posts"));
    }

    @Test
    void getPostList() throws Exception {
        mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/api/post/list"))
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    void getPost() {
    }

    @Test
    void register() {
    }

    @Test
    void modification() {
    }

    @Test
    void remove() {
    }
}