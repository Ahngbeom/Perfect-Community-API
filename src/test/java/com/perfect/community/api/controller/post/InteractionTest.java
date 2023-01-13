package com.perfect.community.api.controller.post;

import com.perfect.community.api.controller.ControllerIntegrationTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class InteractionTest extends ControllerIntegrationTest {

    @Autowired
    protected PostInteractionController controller;

    @BeforeEach
    void setUp() {
        setUpWithController(controller);
    }

    @Test
    void increaseViews() throws Exception {
        mvcResult = mockMvc.perform(MockMvcRequestBuilders.patch("/api/post/views/" + 1))
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    @WithUserDetails("admin")
    void increaseRecommend() throws Exception {
        mvcResult = mockMvc.perform(MockMvcRequestBuilders.patch("/api/post/recommend/" + 1))
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    @WithUserDetails("admin")
    void increaseNotRecommend() throws Exception {
        mvcResult = mockMvc.perform(MockMvcRequestBuilders.patch("/api/post/not-recommend/" + 1))
                .andExpect(status().isOk())
                .andReturn();
    }

}
